package org.psalms;

import org.psalms.external.BibleAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.logging.Logger;

@SpringBootApplication
@EnableScheduling
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        LOGGER.fine("Starting App");
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(environment.getProperty("bible.service.api"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Bean
    public BibleAPI bibleAPI() {
        return retrofit().create(BibleAPI.class);
    }

}
