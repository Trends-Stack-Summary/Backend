package com.project.api.repository

import com.project.api.entity.TechBlogApproval
import org.springframework.data.jpa.repository.JpaRepository

interface TechBlogApprovalRepository : JpaRepository<TechBlogApproval, Long> {
}