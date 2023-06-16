package io.devglimpse.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import io.quarkus.hibernate.reactive.panache.PanacheEntity
import io.smallrye.common.constraint.Nullable
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Topic : PanacheEntity() {

    var title : String = ""
    var created : LocalDateTime? = null
    var updated : LocalDateTime? = null

    @Nullable
    @JsonManagedReference(value = "article")
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "topic", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var articles: Set<Article>? = setOf()

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