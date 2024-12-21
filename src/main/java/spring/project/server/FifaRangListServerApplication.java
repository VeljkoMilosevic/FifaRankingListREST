package spring.project.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy()
@Profile("dev")
@EnableScheduling
public class FifaRangListServerApplication {

    // TODO endpoints DANAS
    // TODO exception handler and HTTPS error codes DANAS
    // TODO Unit tests
    // TODO audit
    public static void main(final String[] args) {
        final String log4jConfPath = "src/main/resources/log4j.properties";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SpringApplication.run(FifaRangListServerApplication.class, args);
    }

}
