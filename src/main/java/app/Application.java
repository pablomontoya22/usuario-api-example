package app;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder
		configure(final SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void stating() {
    	System.out.println("****** Iniciando SPRING BOOT APP ******");
    }

    @Transactional
    @EventListener
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        System.out.println("****** Corriendo SPRING BOOT APP ******");
    }
}