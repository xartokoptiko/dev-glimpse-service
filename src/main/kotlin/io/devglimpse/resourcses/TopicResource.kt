package io.devglimpse.resourcses

import io.devglimpse.entities.Topic
import io.devglimpse.services.TopicService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/topics")
class TopicResource(val service : TopicService) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getAllTopics() : Response =
            service.getAllTopics()

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{topic_id}/articles")
    suspend fun getArticles(@PathParam("topic_id") id : Long) : Response =
            service.getArticles(id)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{topic_id}")
    suspend fun getTopic(@PathParam("topic_id") id : Long) : Response =
            service.getTopic(id)

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    suspend fun createTopic(topic: Topic) : Response =
            service.createTopic(topic)


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    suspend fun updateTopic(topic: Topic) : Response =
            service.updateTopic(topic)

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{topic_id}")
    suspend fun deleteTopic(@PathParam("topic_id") id : Long) : Response =
            service.deleteTopic(id)


}