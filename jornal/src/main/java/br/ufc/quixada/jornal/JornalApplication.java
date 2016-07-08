package br.ufc.quixada.jornal;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.ufc.quixada.jornal.securanca.BloqueadorDeAcesso;

@SpringBootApplication
@RequestMapping("/")
public class JornalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JornalApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/secoes/listar";
	}

	@Configuration
	public static class MvConfig extends WebMvcConfigurerAdapter {
		@Autowired
		private BloqueadorDeAcesso autorizadorInteceptor;
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(autorizadorInteceptor); 
		}

	}
}
