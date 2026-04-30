package com.project.api.controller.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class TechBlogSourceSearchCondition(

    @field:NotBlank(message = "Source must not be blank")
    val source: String,

    @field:NotNull(message = "Current page must not be null")
    @field:Min(value = 1, message = "Current page must be greater than 0")
    var page: Int,

    @field:NotNull(message = "Page size must not be null")
    @field:Min(value = 1, message = "Page size must be greater than 0")
    @field:Max(value = 20, message = "Page size must be less than or equal to 20")
    var size: Int,
)