package mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import({AppConfig.class, WebConfig.class}) // spring boot에서는 자동으로 import함. test 환경에서는 따로 import 해야 함.
public class MySiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }
}
