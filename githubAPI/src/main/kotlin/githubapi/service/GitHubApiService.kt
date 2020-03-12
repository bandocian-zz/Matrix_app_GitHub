package githubapi.service

import com.beust.klaxon.Klaxon
import githubapi.dto.CommitData
import githubapi.dto.Event
import githubapi.dto.Stats
import githubapi.dto.dateConverter
import khttp.get
import khttp.structures.authorization.BasicAuthorization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component("gitHubApiService")
class GitHubApiService(val authUser: String, val authPassword: String) {

    val githubUrl = "https://api.github.com"

    fun getCommitUrls(username: String, from: Date = Date(Long.MIN_VALUE), to: Date = Date(Long.MAX_VALUE)): Set<String> {
        val response = get("$githubUrl/users/$username/events", params = mapOf(Pair("per_page","1000")), auth=BasicAuthorization(authUser, authPassword))
        return if(response.statusCode == 200) {
            val events = Klaxon().converter(dateConverter).parseArray<Event>(response.text)
            events?.filter { it.type == "PushEvent" && it.createdAt.after(from) && it.createdAt.before(to)}
                ?.flatMap { it.payload.commits }
                ?.map { it.url }
                ?.toSet()
                ?: emptySet()
        } else {
            emptySet()
        }
    }

    fun getStats(urls: Set<String>): List<Stats> {
        return urls.mapNotNull { url ->
            val response = get(url, auth=BasicAuthorization(authUser, authPassword))
            Klaxon().parse<CommitData>(response.text)?.stats
        }
    }

}