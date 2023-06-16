package io.devglimpse.services

import io.devglimpse.entities.Article
import io.devglimpse.entities.Comment
import io.devglimpse.entities.ArticleLike
import io.devglimpse.repositories.ArticleRepository
import io.devglimpse.repositories.CommentRepository
import io.devglimpse.repositories.LikeRepository
import io.devglimpse.utils.FileUtil
import io.quarkus.logging.Log
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.coroutines.awaitSuspending
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response
import io.vertx.core.json.JsonObject
import org.jboss.resteasy.reactive.multipart.FileUpload
import java.io.File
import java.io.FileOutputStream

@ApplicationScoped
class ArticleService(
    val articleRepository: ArticleRepository,
    val commentRepository: CommentRepository,
    val likeRepository: LikeRepository,
    val fileUtil: FileUtil
) {

    suspend fun getAllArticles(): Response = articleRepository
        .listAll().onItem().transform { Response.ok(it).build() }.awaitSuspending()

    suspend fun getArticle(id: Long): Response = articleRepository.findById(id)
        .onItem().transform { Response.ok(it).build() }
        .replaceIfNullWith(Response.status(Response.Status.NOT_FOUND).build())
        .awaitSuspending()

    suspend fun getArticlesTopic(id: Long): Response = articleRepository
        .findById(id).onItem().transform { Response.ok(it.topic).build() }
        .replaceIfNullWith(Response.status(Response.Status.BAD_REQUEST).build())
        .awaitSuspending()

    suspend fun createArticle(article: Article, file : FileUpload): Response =
        articleRepository.persistAndFlush(article)
            .onItem().ifNotNull()
            .call { _ -> Uni.createFrom().item(uploadArticleMD(
                file,
                article.topic!!.title,
                article.title
            ))}
            .onItem().transform { Response.ok(it).build() }
//            .onFailure().recoverWithItem(Response.status(Response.Status.BAD_REQUEST).build())
            .awaitSuspending()

    suspend fun updateArticle(article: Article): Response = articleRepository
        .update(
            "title=?1, summary=?2 ,where id=?3",
            article.title,
            article.summary,
            article.id
        ).onItem()
        .transform { if (it == 1) Response.ok(it).build() else Response.status(Response.Status.BAD_REQUEST).build() }
        .awaitSuspending()

    suspend fun updateArticlePartly(id: Long, updates: JsonObject): Response {
        val query: String = updates.joinToString(separator = " , ") {
            if (it.value is String) {
                "${it.key}='${it.value}'"
            } else {
                "${it.key}=${it.value}"
            }
        } + " where id=$id"
        Log.info(query)
        return articleRepository.update(query)
            .onItem().transform {
                if (it.equals(1)) Response.ok().build() else Response.status(Response.Status.NOT_FOUND).build()
            }.awaitSuspending()
    }

    suspend fun deleteArticle(id: Long): Response =
        articleRepository.deleteById(id)
            .onItem()
            .transform { if (it) Response.ok().build() else Response.status(Response.Status.BAD_REQUEST).build() }
            .call { _ -> articleRepository.flush() }
            .awaitSuspending()

    /*
    * Information Getters/updaters for :
    * @Likes
    * @Comments
    */

    suspend fun getArticlesLikes(id: Long): Response = articleRepository
        .findById(id)
        .onItem().transform { Response.ok(it.articleLikes).build() }
        .replaceIfNullWith(Response.status(Response.Status.NOT_FOUND).build())
        .awaitSuspending()


    suspend fun getArticlesComments(id: Long): Response = articleRepository
        .findById(id)
        .onItem().transform { Response.ok(it.comments).build() }
        .replaceIfNullWith(Response.status(Response.Status.NOT_FOUND).build())
        .awaitSuspending()

    suspend fun addLikeToArticle(articleLike: ArticleLike): Response =
        likeRepository.persistAndFlush(articleLike)
            .onItem().transform { Response.ok(it).build() }
            .onFailure().recoverWithItem(Response.status(Response.Status.BAD_REQUEST).build())
            .awaitSuspending()

    suspend fun addCommentToArticle(comment: Comment): Response =
        commentRepository.persistAndFlush(comment)
            .onItem().transform { Response.ok(it).build() }
            .onFailure().recoverWithItem(Response.status(Response.Status.BAD_REQUEST).build())
            .awaitSuspending()

    suspend fun deleteCommentFromArticle(id: Long): Response =
        commentRepository.deleteById(id)
            .onItem()
            .transform { if (it) Response.ok().build() else Response.status(Response.Status.BAD_REQUEST).build() }
            .call { _ -> commentRepository.flush() }
            .awaitSuspending()

    suspend fun deleteLikeFromArticle(id: Long): Response =
        likeRepository.deleteById(id)
            .onItem()
            .transform { if (it) Response.ok().build() else Response.status(Response.Status.BAD_REQUEST).build() }
            .call { _ -> likeRepository.flush() }
            .awaitSuspending()

    fun uploadArticleMD(part: FileUpload, topic: String, title: String) {

        val path : String = "/dev-glimpse/articles/$topic/"
        fileUtil.createMissingDirectories(path)
        val file = File(path+"${title}.md")
        val outputStream = FileOutputStream(file)
        outputStream.write(part.uploadedFile().toFile().readBytes())
        outputStream.flush()
        outputStream.close()
    }

}