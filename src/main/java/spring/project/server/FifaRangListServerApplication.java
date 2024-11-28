package spring.project.server;

import org.apache.log4j.PropertyConfigurator;
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

    // TODO endpoints, authority vs role
    // TODO cron job
    // TODO exception handler and HTTPS error codes
    // TODO Unit tests
    public static void main(final String[] args) {
        final String log4jConfPath = "src/main/resources/log4j.properties";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("veljko"));
        PropertyConfigurator.configure(log4jConfPath);
        SpringApplication.run(FifaRangListServerApplication.class, args);
    }

}
