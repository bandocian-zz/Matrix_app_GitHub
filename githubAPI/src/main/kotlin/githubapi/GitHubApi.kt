    package githubapi

import githubapi.service.GitHubApiService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class GitHubApi

fun main(args: Array<String>) {
   // SpringApplication.run(GitHubApi::class.java, *args)
    val service = GitHubApiService()
    print(service.getEvents("bandocian"))
}
