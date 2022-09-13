package uk.co.alurachallengerbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport // para paginacao
@EnableCaching
public class AluraChallengerBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraChallengerBeApplication.class, args);
	}

}
