package githubapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class GitHubApi

fun main(args: Array<String>) {
    SpringApplication.run(GitHubApi::class.java, *args)
}