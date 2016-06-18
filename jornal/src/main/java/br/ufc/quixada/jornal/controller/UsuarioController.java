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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
 
	private static final String CADASTRO_USUARIO = "usuario/CadastroUsuario";
	private static final String LISTAR_USUARIOS = "usuario/ListarUsuarios";

	@Autowired
	private UsuarioService cadastroUsuarioService;

	// new Usuario, é para passar o objeto para a view, para manter na edição
	@RequestMapping("/novo")
	public String novo(Usuario usuario, Model model) {		
		model.addAttribute("usuario", new Usuario());
		return CADASTRO_USUARIO;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_USUARIO;
		}
		/*
		 * Lembrar de verificar se já existe um login com o novo login
		 * */
		cadastroUsuarioService.salvar(usuario); 
		attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso.");
		return "redirect:/usuarios/novo";
	}
	
	@RequestMapping("/listar")
	public String listar(Model model){
		List<Usuario> usuarios = cadastroUsuarioService.listar();
		model.addAttribute("users", usuarios);
		return LISTAR_USUARIOS;
	}
	
	@RequestMapping("editar/{id}")
	public String editar(@PathVariable("id") Usuario usuario, Model model){
		model.addAttribute(usuario);
		return CADASTRO_USUARIO;
	}
	
	
	@RequestMapping(value= "excluir/{id}"	)
	public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
		cadastroUsuarioService.excluir(id); 
		return "redirect:/usuarios/listar";
	}
	
	

}