package io.devglimpse.repositories

import io.devglimpse.entities.Topic
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TopicRepository : PanacheRepository<Topic> {
}