package br.ufc.quixada.jornal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	@RequestMapping(value="/administracao")
	public String paginaAdministracao(){
		return "editor/IndexEditor";
	}

}
