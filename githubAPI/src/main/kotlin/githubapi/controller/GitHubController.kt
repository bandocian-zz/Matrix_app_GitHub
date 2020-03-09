package githubapi.controller

import com.beust.klaxon.Klaxon
import githubapi.dto.Stats
import githubapi.service.GitHubApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GitHubController(val gitHubApiService: GitHubApiService) {

    @GetMapping("/github")
    fun github(@RequestParam(value = "username") username: String) : String {
        val urls = gitHubApiService.getCommitUrls(username)
        val stats = gitHubApiService.getStats(urls)
        return Klaxon().toJsonString(stats)

    }

}