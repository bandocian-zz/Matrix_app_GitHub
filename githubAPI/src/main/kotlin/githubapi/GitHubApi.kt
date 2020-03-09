    package githubapi

import githubapi.service.GitHubApiService
import org.springframework.boot.autoconfigure.SpringBootApplication

    @SpringBootApplication
class GitHubApi

fun main(args: Array<String>) {
   // SpringApplication.run(GitHubApi::class.java, *args)
    val service = GitHubApiService()
    val urls = service.getCommitUrls("bandocian")
    println(urls)
    val stats = service.getStats(urls)
    print(stats)
}
