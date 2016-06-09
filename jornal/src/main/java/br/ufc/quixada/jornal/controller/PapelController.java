package br.ufc.quixada.jornal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.jornal.model.Papel;
import br.ufc.quixada.jornal.service.PapelService;

@Controller
@RequestMapping("/papeis")
public class PapelController {

	private static final String CADASTRO_PAPEL = "CadastroPapel";
	private static final String LISTAR_PAPEIS = "ListarPapeis";

	@Autowired
	private PapelService papelService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(CADASTRO_PAPEL);
		modelAndView.addObject(new Papel());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Papel papel, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CADASTRO_PAPEL;
		}

		papelService.salvar(papel);
		attributes.addFlashAttribute("mensagem", "Papel cadastrado com sucesso.");
		return "redirect:/papeis/novo";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model){
		List<Papel> papels = papelService.listar();
		model.addAttribute("papels", papels);
		return LISTAR_PAPEIS;
	}
	 
	@RequestMapping("editar/{id}")
	public String editar(@PathVariable("id") Papel papel, Model model){
		model.addAttribute("papel", papel);
		return CADASTRO_PAPEL;
	}
	
	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id){
		papelService.excluir(id);
		return "redirect:/papeis/listar";
	}
	
}
