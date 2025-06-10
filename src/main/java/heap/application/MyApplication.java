package heap.application;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"heap.application.stalls", "heap.application.Meal"})
public class MyApplication {
    
    private static final Logger log = LoggerFactory.getLogger(MyApplication.class);
    
	public static void main(String[] args) {
        // System.setProperty("spring.datasource.username", dotenv.get("DATABASE_USER"));
        // System.setProperty("spring.datasource.password", dotenv.get("DATABASE_PASSWORD"));
        // System.setProperty("spring.datasource.url",url);
		SpringApplication.run(MyApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource() {
        Dotenv dotenv = Dotenv.load();
        String url = "spring.datasource.url=jdbc:postgresql://" + dotenv.get("DATABASE_HOST") + ":" + dotenv.get("DATABASE_PORT") + "/" + dotenv.get("DATABASE_NAME");
        return DataSourceBuilder.create()
            .url("jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.hagiswlsyabukbhihldd&password=" + dotenv.get("DATABASE_PASSWORD"))
            .username(dotenv.get("DATABASE_USER"))
            .password(dotenv.get("DATABASE_PASSWORD"))
            .driverClassName("org.postgresql.Driver")
            .build();
    }
}


