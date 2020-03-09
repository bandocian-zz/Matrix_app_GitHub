package githubapi.service

import com.beust.klaxon.Klaxon
import githubapi.dto.*
import khttp.get
import org.springframework.stereotype.Component

@Component("teamService")
class TeamService(val allTeams : Teams) {

    fun findTeamByName(teamName: String): Team {
        return allTeams.teams.find { it.teamName == teamName } ?: throw Exception("Team not found")
    }

}