/*package br.ufc.quixada.jornal.securanca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptador extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest requisicao, HttpServletResponse resposta, Object handler)
			throws Exception {

		String uri = requisicao.getRequestURI();
		// para todos os usuários
		if (uri.endsWith("efetuarLogin") || uri.endsWith("secoes/listar") || uri.endsWith("secoes/listar")
				|| uri.endsWith("noticias/listar")) {
			return true;
		}
		// Usuario usuario = (Usuario)
		// requisicao.getSession().getAttribute("usuarioLogado");

		if (requisicao.getSession().getAttribute("usuarioLogado") != null) {
			// se o usuário.papel = JORNALISTA
			// if(usuario.getPapeis().get(0).getId().){
			// dou as permissões
			// }
			return true;
		}

		resposta.sendRedirect("efetuarLoginFormulario");
		return false;
	}
}*/

package br.ufc.quixada.jornal.securanca;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class BloqueadorDeAcesso extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(request.getSession().getAttribute("usuarioLogado")!=null){
			return true;
		}
		
		response.sendRedirect("/login/efetuarLogin");
		return false;
	}
}
