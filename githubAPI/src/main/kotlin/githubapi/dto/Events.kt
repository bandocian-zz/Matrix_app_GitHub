package githubapi.dto

import com.beust.klaxon.Converter
import com.beust.klaxon.Json
import com.beust.klaxon.JsonValue
import java.text.SimpleDateFormat
import java.util.*

data class Event(val type: String, val payload: Payload, @Json(name = "created_at")val createdAt: Date)

data class Payload(val size: Int = 0, val commits: List<Commit> = emptyList())

data class Commit(val url: String)

val dateConverter = object: Converter {
    override fun canConvert(cls: Class<*>): Boolean = cls == Date::class.java

    override fun fromJson(jv: JsonValue): Date {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return formatter.parse(jv.string)
    }

    override fun toJson(value: Any): String = """{"created_at" = $value}"""

}