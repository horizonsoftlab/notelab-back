package sn.hsl.notelabback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class NotelabBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotelabBackApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner() {
		return args -> {
			log.info("NOTELAB application running...");
		};
	}
}
