package io.devglimpse.resourcses.gcl

import io.devglimpse.entities.Topic
import io.devglimpse.repositories.TopicRepository
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class TopicGCL(private val repository: TopicRepository) {
    @Query("campuses")
    @Description("Get peaces from all the campuses ")
    fun getTopics(): Uni<MutableList<Topic>> =
        repository.findAll().list<Topic>()

    @Query("campus")
    @Description("Get peaces from a campus")
    fun getTopic(id: Long): Uni<Topic> =
        repository.findById(id)
}