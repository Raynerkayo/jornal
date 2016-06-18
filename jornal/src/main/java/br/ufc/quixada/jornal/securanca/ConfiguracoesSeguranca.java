package br.ufc.quixada.jornal.securanca;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ConfiguracoesSeguranca extends WebSecurityConfigurerAdapter{
		
	@Override
	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests()
////			.antMatchers("/noticias/nova").hasRole("JORNALISTA")
////			.antMatchers("/noticias/excluir").hasRole("JORNALISTA")
////			.antMatchers("/noticias/excluir").hasRole("EDITOR")
////			.antMatchers("/editor/**").hasRole("EDITOR")			
////			.antMatchers("/index").permitAll()
////			.antMatchers("/noticias/listar").permitAll()
//			.antMatchers("/login/**").permitAll()
//			.anyRequest().authenticated()
//			.and().formLogin();
	}	
}
