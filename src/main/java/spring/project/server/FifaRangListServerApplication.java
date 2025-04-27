package spring.project.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy()
@Profile("dev")
@EnableScheduling
public class FifaRangListServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FifaRangListServerApplication.class, args);
    }

}
