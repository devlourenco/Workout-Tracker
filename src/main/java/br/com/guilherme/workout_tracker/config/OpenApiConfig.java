package br.com.guilherme.workout_tracker.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI workoutTrackerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workout Tracker API")
                        .description("API REST para gerenciamento de treinos, exercícios e séries")
                        .version("1.0.0")
                );
    }
}
