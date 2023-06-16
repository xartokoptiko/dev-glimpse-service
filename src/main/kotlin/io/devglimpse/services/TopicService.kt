package io.devglimpse.services

import io.devglimpse.entities.Topic
import io.devglimpse.repositories.TopicRepository
import io.smallrye.mutiny.coroutines.awaitSuspending
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response


@ApplicationScoped
class TopicService(val topicRepository: TopicRepository) {

    suspend fun getAllTopics(): Response = topicRepository
            .listAll().onItem().transform { Response.ok(it).build() }.awaitSuspending()

    suspend fun getTopic(id: Long): Response = topicRepository.findById(id)
            .onItem().transform { Response.ok(it).build() }.replaceIfNullWith(Response.status(Response.Status.NOT_FOUND).build())
            .awaitSuspending()

    suspend fun getArticles(id : Long) : Response = topicRepository.findById(id)
            .onItem().transform { Response.ok(it.articles).build() }.replaceIfNullWith(Response.status(Response.Status.NOT_FOUND).build())
            .awaitSuspending()

    suspend fun createTopic(topic: Topic): Response =
        topicRepository.persistAndFlush(topic)
            .onItem().transform { Response.ok(it).build() }
//            .onFailure().recoverWithItem(Response.status(Response.Status.BAD_REQUEST).build())
            .awaitSuspending()


    suspend fun updateTopic(topic: Topic): Response = topicRepository
            .update(
                    "title=?1, where id=?2",
                    topic.title,
                    topic.id
            ).onItem()
            .transform { if (it == 1) Response.ok(it).build() else Response.status(Response.Status.BAD_REQUEST).build() }
            .awaitSuspending()

    suspend fun deleteTopic(id: Long): Response =
            topicRepository.deleteById(id)
                    .onItem()
                    .transform { if (it) Response.ok().build() else Response.status(Response.Status.BAD_REQUEST).build() }
                    .call { _ -> topicRepository.flush() }
                    .awaitSuspending()
}