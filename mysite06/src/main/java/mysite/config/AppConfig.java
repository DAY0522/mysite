package mysite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableAspectJAutoProxy // 따로 안 해도 설정이 돼있음.
//@EnableTransactionManagement
// import, componentscan 모두 자동 스캔함.
//@Import({DBConfig.class, MyBatisConfig.class, SecurityConfig.class})
//@ComponentScan(basePackages={"mysite.service", "mysite.repository", "mysite.aspect"})
public class AppConfig {
}
