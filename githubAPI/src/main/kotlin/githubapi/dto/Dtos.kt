package githubapi.dto

import com.beust.klaxon.JsonObject
import org.json.JSONObject

data class Event(val type: String, val actor: Actor, val payload: Payload, val created_at: String)

data class Actor(val id: Int, val login: String, val url : String)

data class Payload(val size: Int = 0, val commits: List<Commit> = emptyList())

data class Commit(val url: String)