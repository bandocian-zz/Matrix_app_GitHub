package githubapi

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config{

    @Bean
    fun authUser(): String = "bandocian"

    @Bean
    fun authPassword(): String = "8fb2bf43f219c6dbb5695481aad1d32530143b94"

}