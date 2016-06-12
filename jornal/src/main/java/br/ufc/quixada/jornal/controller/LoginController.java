package br.ufc.quixada.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.securanca.Criptografia;
import br.ufc.quixada.jornal.securanca.Login;
import br.ufc.quixada.jornal.service.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private Criptografia criptografar;
	
	@RequestMapping("/formularioLogin")
	public String formLogin(){
		return "FormularioDeLogin";
	}
	
	@RequestMapping("/efetuarLogin")
	public String efetuarLogin(Login loginForm, HttpSession session){
		Usuario usuario = usuarioService.logar(loginForm.getLogin());
		if(usuario != null){
			if(criptografar.criptografarSenha(loginForm.getSenha()).equals(usuario.getSenha())){
				//lembrar de pegar o perfil do usuários, se tiver doids levar para a pagina determinada
				session.setAttribute("usuario_logado", usuario);
				return "redirect:/papeis/listar";
			}
		}
		return "redirect:/papeis/novo";
	} 
	
	@RequestMapping("/efetuarLogout")
	public String efetuarLogout(HttpSession session){
		session.invalidate();
		//Colocar para formulario inicial, com conteudo para não cadastrados
		return "redirect:formularioLogin";
	}
	
}
