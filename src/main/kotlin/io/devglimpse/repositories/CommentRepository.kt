package io.devglimpse.repositories

import io.devglimpse.entities.Comment
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CommentRepository : PanacheRepository<Comment> {
}