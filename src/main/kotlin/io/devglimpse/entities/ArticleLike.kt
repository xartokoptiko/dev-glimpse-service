package io.devglimpse.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import io.quarkus.hibernate.reactive.panache.PanacheEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class ArticleLike : PanacheEntity() {

    var created : LocalDateTime? = null
    var updated : LocalDateTime? = null

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Article::class)
    @JsonBackReference(value = "like_of_article")
    var article: Article? = null



    @PrePersist
    fun addCreateTime() {
        created = LocalDateTime.now()
        updated = LocalDateTime.now()
    }

    @PreUpdate
    fun addUpdateTime() {
        updated = LocalDateTime.now()
    }
}