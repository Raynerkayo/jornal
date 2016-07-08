package br.ufc.quixada.jornal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/jornalista")
public class JornalistaController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String paginaJornalista() {
		return "IndexJornalista";
	}

}
