package br.ufc.quixada.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.jornal.model.Usuario;

@Controller
@RequestMapping("/editor")
public class EditorController {

	@RequestMapping(value = "/administracao")
	public String paginaAdministracao(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("editor")) {
			return "editor/IndexEditor";
		}else{
			return "PermissaoNegada";
		}
	}

}
