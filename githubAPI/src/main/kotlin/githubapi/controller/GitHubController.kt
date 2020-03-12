package githubapi.controller

import com.beust.klaxon.Klaxon
import githubapi.core.WeightCalculator
import githubapi.dto.MemberStats
import githubapi.dto.Stats
import githubapi.dto.TeamStats
import githubapi.service.GitHubApiService
import githubapi.service.TeamService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@RestController
class GitHubController(val teamService: TeamService, val gitHubApiService: GitHubApiService) {

    @GetMapping("/github")
    fun github(
        @RequestParam(value = "username") username: String,
        @RequestParam(required = false) from: String?,
        @RequestParam(required = false) to: String?
    ): String {
        val fromDate = SimpleDateFormat("yyyy-MM-dd").parse(from ?: "0000-01-01")
        val toDate = SimpleDateFormat("yyyy-MM-dd").parse(to ?: "3000-01-01")
        val urls = gitHubApiService.getCommitUrls(username, fromDate, toDate)
        val stats = gitHubApiService.getStats(urls)
        return Klaxon().toJsonString(WeightCalculator.calculateWeight(stats))
    }

    @GetMapping("/githubTeam")
    fun githubTeam(@RequestParam(value = "team") team: String): String {
        val currentTeam = teamService.findTeamByName(team)
        val membersStats: MutableList<MemberStats> = ArrayList()

        currentTeam.members.forEach {
            val urls = gitHubApiService.getCommitUrls(it.githubUsername)
            val stats = gitHubApiService.getStats(urls)

            membersStats.add(MemberStats(it, stats))
        }

        return Klaxon().toJsonString(membersStats)
    }

    @GetMapping("/githubTeams")
    fun githubTeam(): String {
        val teams = teamService.allTeams.teams
        val teamStats: MutableList<TeamStats> = ArrayList()

        teams.forEach() {
            var stats: List<Stats>
            var total: Int = 0
            var additions: Int = 0
            var deletions: Int = 0
            it.members.forEach { member ->
                val urls = gitHubApiService.getCommitUrls(member.githubUsername)
                stats = gitHubApiService.getStats(urls)
                stats.forEach { stat ->
                    total += stat.total
                    additions += stat.additions
                    deletions += stat.deletions
                }
            }
            teamStats.add(TeamStats(it.teamName, Stats(total, additions, deletions)))
        }

        return Klaxon().toJsonString(teamStats)
    }

}