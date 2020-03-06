package githubapi.controller

import githubapi.service.GitHubApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GitHubController(val gitHubApiService: GitHubApiService) {

    @GetMapping("/github")
    fun github(@RequestParam(value = "username") username: String) =
        gitHubApiService.getUserDetails(username)

}