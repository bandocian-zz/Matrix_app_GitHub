package githubapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config{

    @Bean
    fun authUser(): String = "bandocian"

    @Bean
    fun authPassword(): String = "990352a71d246c9b45f941fa73078f7011075afe"

}