package io.devglimpse.resourcses.gcl

import io.devglimpse.entities.Article
import io.devglimpse.repositories.ArticleRepository
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class ArticleGCL(private val repository: ArticleRepository) {
    @Query("articles")
    @Description("Get peaces from all the Articles ")
    fun getArticles(): Uni<MutableList<Article>> =
        repository.findAll().list<Article>()

    @Query("article")
    @Description("Get peaces from an Articles")
    fun getArticle(id: Long): Uni<Article> =
        repository.findById(id)
}