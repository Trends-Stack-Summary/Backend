package com.project.api.entity

import jakarta.persistence.*

@Entity
@Table(name = "tech_blog_approval")
class TechBlogApproval {

    @Id
     val id: Long? = null

    @MapsId
    @JoinColumn(name = "tech_blog_id")
    @OneToOne(fetch = FetchType.LAZY)
     var techBlog: TechBlog? = null

    @Column(columnDefinition = "text")
     var summary: String? = null
}