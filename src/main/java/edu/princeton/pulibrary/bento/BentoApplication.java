package edu.princeton.pulibrary.bento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class BentoApplication {
    /**
     * Run the spring application.
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(BentoApplication.class, args);
	}

    /**
     * Authorize the routes that will be used in the application.
     * @param http
     * @return
     * @throws Exception
     */
	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests ->
            requests.requestMatchers("/search/catalog").anonymous()
                                                                   .anyRequest()
                                                                   .authenticated());
		return http.build();
	}

}
