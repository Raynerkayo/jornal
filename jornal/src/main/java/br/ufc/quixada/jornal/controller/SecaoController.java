package br.ufc.quixada.jornal.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.jornal.model.Secao;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.SecaoService;

@Controller
@RequestMapping("/secoes")
public class SecaoController {

	private static String LISTAR_SECOES = "index";
	private static String CADASTRAR_SECAO = "secao/CadastrarSecao";
	
	@Autowired
	private SecaoService secaoService;
	
	@RequestMapping(value = "/nova", method=RequestMethod.GET)
	public String novaSecao(Model model, HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if(usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("editor")){
		model.addAttribute(new Secao());
		return CADASTRAR_SECAO;
		} else{
			return "PermissaoNegada";
		}
	}
	
	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public String salvarSecao(@Validated Secao secao, Errors errors, RedirectAttributes attributes, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if(usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("editor")){
		if (errors.hasErrors()) {
			return CADASTRAR_SECAO;
		}
		
		secaoService.salvar(secao);
		attributes.addFlashAttribute("mensagem", "Seção cadastrada com sucesso."); 
		return "redirect:/secoes/listar";}
		else{
			return "PermissaoNegada";
		}
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarSecoes(Model model){
		List<Secao> secoes = secaoService.listar();
		model.addAttribute("secoes", secoes);
		return LISTAR_SECOES;
	}
	
}
