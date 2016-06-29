package br.ufc.quixada.jornal.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Oferta;
import br.ufc.quixada.jornal.service.ClassificadoService;
import br.ufc.quixada.jornal.service.OfertaService;
import br.ufc.quixada.jornal.service.UsuarioService;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {

	private static String CADASTRAR_OFERTA = "CadastrarOferta";
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ClassificadoService classificadoService;
	
	@RequestMapping(value = "/nova/{id}", method = RequestMethod.GET)
	public String nova(@PathVariable("id") Long id, Model model){
		model.addAttribute("classificado", classificadoService.buscarPorId(id));
		model.addAttribute(new Oferta());
		return CADASTRAR_OFERTA;
	}
	
	
	@RequestMapping(value = "/nova/{id}", method = RequestMethod.POST)
	public String salvar(@Validated Oferta oferta, @PathVariable("id") Long id, Model model){
		oferta.setClassificado(classificadoService.buscarPorId(id));
		oferta.setUsuario(service.buscarPorId(1L));
		oferta.setDataOferta(new Date());
		ofertaService.salvar(oferta);
		return "redirect:/classificados/novo";
	}
			
}