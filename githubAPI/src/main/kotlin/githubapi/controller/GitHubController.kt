package githubapi.controller

import com.beust.klaxon.Klaxon
import githubapi.dto.*
import githubapi.service.GitHubApiService
import githubapi.service.TeamService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class GitHubController(val teamService: TeamService, val gitHubApiService: GitHubApiService) {

    @GetMapping("/github")
    fun github(@RequestParam(value = "username") username: String) : String {
        val urls = gitHubApiService.getCommitUrls(username)
        val stats = gitHubApiService.getStats(urls)
        return Klaxon().toJsonString(stats)
    }

    @GetMapping("/githubTeam")
    fun githubTeam(@RequestParam(value = "team") team: String) : String {
        val currentTeam = teamService.findTeamByName(team)
        val membersStats: MutableList<MemberStats> = ArrayList()

        currentTeam.members.forEach {
            val urls = gitHubApiService.getCommitUrls(it.githubUsername)
            val stats = gitHubApiService.getStats(urls)

            membersStats.add(MemberStats(it,stats))
        }

        return Klaxon().toJsonString(membersStats)
    }

}