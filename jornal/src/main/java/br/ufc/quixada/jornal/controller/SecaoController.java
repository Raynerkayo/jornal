package br.ufc.quixada.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.jornal.model.Secao;
import br.ufc.quixada.jornal.service.SecaoService;

@Controller
@RequestMapping("/secoes")
public class SecaoController {

	private static String LISTAR_SECOES = "secao/ListarSecoes";
	private static String CADASTRAR_SECAO = "secao/CadastrarSecao";
	
	@Autowired
	private SecaoService secaoService;
	
	@RequestMapping(value = "/nova", method=RequestMethod.GET)
	public String novaSecao(Model model){
		model.addAttribute(new Secao());
		return CADASTRAR_SECAO;
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public String salvarSecao(@Validated Secao secao, Errors errors, RedirectAttributes attributes, HttpSession session) {
		
		if (errors.hasErrors()) {
			return CADASTRAR_SECAO;
		}
		
		secaoService.salvar(secao);
		attributes.addFlashAttribute("mensagem", "Seção cadastrada com sucesso."); 
		return "redirect:/secoes/listar";
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarSecoes(Model model){
		List<Secao> secoes = secaoService.listar();
		model.addAttribute("secoes", secoes);
		return LISTAR_SECOES;
	}
	
	@RequestMapping(value = "/excluir/{id}")
	public String remover(@PathVariable("id") Long id){
		secaoService.excluir(id);
		return "redirect:/secoes/listar";
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable("id") Secao secao, Model model){
		model.addAttribute(secao);
		return CADASTRAR_SECAO;
	}
	
	
	
}
