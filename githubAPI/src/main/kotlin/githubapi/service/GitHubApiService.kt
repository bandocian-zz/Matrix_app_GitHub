package githubapi.service

import githubapi.dto.UsernameDetailsDto
import khttp.get
import org.springframework.stereotype.Component

@Component("gitHubApiService")
class GitHubApiService {

    val githubUrl = "https://api.github.com"

    fun getUserDetails(username: String) : UsernameDetailsDto {
        val response = get(githubUrl + "/users/" + username)

        return  UsernameDetailsDto(response.text)
    }

}