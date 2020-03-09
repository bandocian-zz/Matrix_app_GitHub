package githubapi.dto

data class Teams(val teams: List<Team> = emptyList())

data class Team(val teamName: String, val jiraProjectId: Int = 0, val members: List<Member> = emptyList())

data class Member(val name: String, val githubUsername: String, val jiraUsername: String)