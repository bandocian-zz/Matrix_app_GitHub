package githubapi.service

import com.beust.klaxon.Klaxon
import githubapi.dto.CommitData
import githubapi.dto.Event
import githubapi.dto.Stats
import khttp.get
import org.springframework.stereotype.Component

@Component("gitHubApiService")
class GitHubApiService {

    val githubUrl = "https://api.github.com"

    fun getCommitUrls(username: String): Set<String> {
        val response = get("$githubUrl/users/$username/events")
        val events = Klaxon().parseArray<Event>(response.text)
        return events?.filter { it.type == "PushEvent" }
            ?.flatMap { it.payload.commits }
            ?.map { it.url }
            ?.toSet()
            ?: emptySet()
    }

    fun getStats(urls: Set<String>): List<Stats> {
        return urls.mapNotNull { url ->
            val response = get(url)
            Klaxon().parse<CommitData>(response.text)?.stats
        }
    }

//


}