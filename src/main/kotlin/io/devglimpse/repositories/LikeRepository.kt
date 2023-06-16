package io.devglimpse.repositories

import io.devglimpse.entities.ArticleLike
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class LikeRepository : PanacheRepository<ArticleLike> {
}