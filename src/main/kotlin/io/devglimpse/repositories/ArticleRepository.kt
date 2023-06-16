package io.devglimpse.repositories

import io.devglimpse.entities.Article
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ArticleRepository : PanacheRepository<Article> {
}