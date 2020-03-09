package githubapi.service

import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import githubapi.dto.*
import khttp.get
import org.json.JSONObject
import org.springframework.stereotype.Component

@Component("gitHubApiService")
class GitHubApiService {

    val githubUrl = "https://api.github.com"

    fun getUserDetails(username: String): UsernameDetailsDto {
        val response = get("$githubUrl/users/$username")

        return UsernameDetailsDto(response.text)
    }

    fun getEvents(username: String): List<String> {
        val response = get("$githubUrl/users/$username/events")
        val events = Klaxon().parseArray<Event>(response.text)
        val result = events?.filter { it.type == "PushEvent" }?.flatMap { it.payload.commits }?.map {it.url} ?: emptyList()
        return result
    }

//


}