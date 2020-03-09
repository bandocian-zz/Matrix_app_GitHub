    package githubapi

import com.beust.klaxon.Klaxon
import githubapi.dto.Teams
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource

@SpringBootApplication
class GitHubApi {

    @Value("classpath:settings.json")
    lateinit var teamsResource: Resource;

    @Bean("allTeams")
    fun teams() : Teams =
        Klaxon().parse<Teams>(teamsResource.file.readText()) ?: Teams()

    }

fun main(args: Array<String>) {
    SpringApplication.run(GitHubApi::class.java, *args)
}
