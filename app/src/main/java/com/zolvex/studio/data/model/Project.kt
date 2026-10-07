package com.zolvex.studio.data.model

data class Project(
    val id: String,
    val name: String,
    val lastEditedMillis: Long,
    val templateId: String?
)
