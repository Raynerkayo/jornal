package br.ufc.quixada.jornal;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;


@SpringBootApplication
public class JornalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JornalApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	@Configuration
	public static class MvConfig extends WebMvcConfigurerAdapter{
		
		@Override
		public void addViewControllers(ViewControllerRegistry registry){
			registry.addRedirectViewController("/", "/noticias/listar"); 
		}
		
	}
	
}
