package githubapi.dto

import com.beust.klaxon.Json

data class Event(val type: String, val payload: Payload, @Json(name = "created_at")val createdAt: String)

data class Payload(val size: Int = 0, val commits: List<Commit> = emptyList())

data class Commit(val url: String)