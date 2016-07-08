package br.ufc.quixada.jornal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Classificado;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	private static String CADASTRAR_CLASSIFICADO = "editor/CadastrarClassificado";

	@RequestMapping(value = "/cadastrar/classificado/novo", method = RequestMethod.GET)
	public String novoClassificado(Classificado classificado, Model model) {
		model.addAttribute(new Classificado());
		return CADASTRAR_CLASSIFICADO;
	}
	
	@RequestMapping(value="/administracao")
	public String paginaAdministracao(){
		return "editor/IndexEditor";
	}

}
