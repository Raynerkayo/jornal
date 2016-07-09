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

import br.ufc.quixada.jornal.model.Papel;
import br.ufc.quixada.jornal.model.Usuario;

@Component
public class BloqueadorDeAcesso extends HandlerInterceptorAdapter {

	private static String JORNALISTA;
	private static String EDITOR;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.endsWith("/") || uri.endsWith("/login/efetuarLogin") || uri.endsWith("/secoes/listar")
				|| uri.endsWith("/usuarios/novo") || uri.endsWith("/classificados/listar")
				|| uri.startsWith("/noticias/secao/") || uri.startsWith("/comentario/listar/")
				|| uri.startsWith("/noticias/listar") || uri.startsWith("PermissaoNegada") || uri.startsWith("/css/")
				|| uri.startsWith("/js/")) {

			return true;

		}

		if (request.getSession().getAttribute("usuarioLogado") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			if (!usuario.getPapeis().isEmpty()) {
				for (Papel papel : usuario.getPapeis()) {
					if (papel.getPapelNome().equalsIgnoreCase(EDITOR)) {
						uri.startsWith("/editor");
							return true;
						
					} else if (papel.getPapelNome().equalsIgnoreCase(JORNALISTA)) {
						uri.startsWith("/jornalista");
						return true;
					}
				}
			}
			return true;
		}

		response.sendRedirect("/login/efetuarLogin");
		return false;
	}
}
