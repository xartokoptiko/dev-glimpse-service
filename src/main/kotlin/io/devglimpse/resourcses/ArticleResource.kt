package io.devglimpse.resourcses

import io.devglimpse.entities.Article
import io.devglimpse.entities.Comment
import io.devglimpse.entities.ArticleLike
import io.devglimpse.services.ArticleService
import org.jboss.resteasy.reactive.PartType
import org.jboss.resteasy.reactive.multipart.FileUpload
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/articles")
class ArticleResource(val service: ArticleService) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getAllArticles(): Response =
        service.getAllArticles()


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{article_id}")
    suspend fun getTopic(@PathParam("article_id") id: Long): Response =
        service.getArticlesTopic(id)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    suspend fun createArticle(@FormParam("json") @PartType(MediaType.APPLICATION_JSON)article: Article,
                              @FormParam("file") articleFile : FileUpload): Response =
        service.createArticle(article, articleFile)


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    suspend fun updateArticle(article: Article): Response =
        service.updateArticle(article)

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{article_id}")
    suspend fun deleteArticle(@PathParam("article_id") id: Long): Response =
        service.deleteArticle(id)

    /*
    * Information updaters for :
    * @Likes
    * @Comments
    */


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add/like")
    suspend fun addArticleLikes(articleLike :ArticleLike): Response =
        service.addLikeToArticle(articleLike)


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add/comment")
    suspend fun addArticleComment(comment : Comment): Response =
        service.addCommentToArticle(comment)

}