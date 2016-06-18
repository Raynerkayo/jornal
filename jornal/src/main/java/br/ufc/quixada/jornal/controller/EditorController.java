package br.ufc.quixada.jornal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.model.Secao;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.SecaoService;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	@Autowired
	private SecaoService secaoService; 

	private static String CADASTRAR_USUARIO = "usuario/CadastroUsuario";
	private static String CADASTRAR_CLASSIFICADO = "editor/CadastrarClassificado";
	private static String CADASTRAR_SECAO = "editor/CadastrarSecao";
	private static String VISAO_GERAL_EDITOR = "editor/Geral";

	@RequestMapping(value = "/cadastrar/usuario", method = RequestMethod.GET)
	public String novoJornalista(Usuario usuario, Model model) {
		// aqui eu chamo a view de cadastrar usuário, e de lá é enviado para o
		// usuário controller.
		model.addAttribute(new Usuario());
		return CADASTRAR_USUARIO;
	}

	@RequestMapping(value = "/cadastrar/classificado", method = RequestMethod.GET)
	public String novoClassificado(Classificado classificado, Model model) {
		model.addAttribute(new Classificado());
		return CADASTRAR_CLASSIFICADO;
	}

	/*
	 * @RequestMapping(value = "/cadastrar/classificado", method =
	 * RequestMethod.POST) public String cadastrarClassificado(@Validated
	 * Classificado classificado, Model model) {
	 * classificadoService.salvar(classificado); return
	 * "redirect:/editor/cadastrar/classificado"; }
	 */
	
	@RequestMapping(value = "/cadastrar/secao", method = RequestMethod.GET)
	public String novaSecao(Model model){
		model.addAttribute(new Secao());
		return CADASTRAR_SECAO;
	}
	
	@RequestMapping(value = "/cadastrar/secao", method = RequestMethod.POST)
	public String salvarSecao(@Validated Secao secao){
		secaoService.salvar(secao);
		return VISAO_GERAL_EDITOR;
	}
	//apagar notícia será feito no controller notícia, então eu capturo o usuário que está logado e suas permissões
}
