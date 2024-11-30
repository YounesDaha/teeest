package ma.xproce.inventoryservice;

import lombok.Builder;
import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
		return args -> {
			// Alimenter les créateurs
			List<Creator> creators = List.of(
					Creator.builder().name("John Doe").email("john.doe@example.com").build(),
					Creator.builder().name("Jane Smith").email("jane.smith@example.com").build()
			);
			creators.forEach(creatorRepository::save);

			// Alimenter les vidéos
			List<Video> videos = List.of(
					Video.builder().name("Introduction to Spring Boot")
							.url("https://example.com/video1")
							.description("An introductory video about Spring Boot.")
							.datePublication(new Date())
							.creator(creators.get(0))
							.build(),
					Video.builder().name("GraphQL with Spring")
							.url("https://example.com/video2")
							.description("Learn GraphQL integration with Spring.")
							.datePublication(new Date())
							.creator(creators.get(1))
							.build()
			);
			videos.forEach(videoRepository::save);

			System.out.println("Database initialized with creators and videos.");
		};
	}

}
