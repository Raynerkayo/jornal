package br.ufc.quixada.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Usuario;

@Controller
@RequestMapping("/jornalista")
public class JornalistaController {

	@RequestMapping(method = RequestMethod.GET)
	public String paginaJornalista(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("jornalista")) {
			return "jornalista/IndexJornalista";
		} else {
			return "PermissaoNegada";
		}
	}

}
