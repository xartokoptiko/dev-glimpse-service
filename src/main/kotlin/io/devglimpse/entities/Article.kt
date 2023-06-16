package io.devglimpse.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.quarkus.hibernate.reactive.panache.PanacheEntity
import io.smallrye.common.constraint.Nullable
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Article : PanacheEntity() {
    @Column(unique = true)
    var title: String = ""
    var summary: String = ""
    var created: LocalDateTime? = null
    var updated: LocalDateTime? = null
    var fileLocation: String = ""

    @Nullable
    @JsonManagedReference(value = "like_of_article")
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var articleLikes: Set<ArticleLike>? = setOf()

    @Nullable
    @JsonManagedReference(value = "comment")
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "article", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var comments: Set<Comment>? = setOf()

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Topic::class)
    @JsonBackReference(value = "article")
    var topic: Topic? = null

    @PrePersist
    fun prePersistFunctions() {
        created = LocalDateTime.now()
        updated = LocalDateTime.now()
        fileLocation = "/dev-glimpse/articles/${topic?.title}/${title}"
    }

    @PreUpdate
    fun preUpdateFunctions() {
        updated = LocalDateTime.now()
        fileLocation = "/dev-glimpse/articles/${topic?.title}/${title}"
    }

}