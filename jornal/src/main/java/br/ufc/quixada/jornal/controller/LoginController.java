package br.ufc.quixada.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.securanca.Login;
import br.ufc.quixada.jornal.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/efetuarLogin", method = RequestMethod.GET)
	public String formLogin() {
		return "FormularioDeLogin";
	}

	@RequestMapping(value = "/efetuarLogin", method = RequestMethod.POST)
	public String efetuarLogin(Login loginForm, HttpSession session) {
		Usuario usuario = usuarioService.logar(loginForm.getLogin(), loginForm.getSenha());
		if (usuario != null) {
			session.setAttribute("usuarioLogado", usuario);
			return "redirect:/papeis/listar";
		}
		return "redirect:/papeis/novo";
	}

	/*
	 * @RequestMapping(value = "/efetuarLogin", method = RequestMethod.POST)
	 * public String efetuarLogin(Login loginForm, HttpSession session){ Usuario
	 * usuario = usuarioService.logar(loginForm.getLogin(),
	 * loginForm.getSenha()); if(usuario != null){
	 * if(criptografar.criptografarSenha(loginForm.getSenha()).equals(usuario.
	 * getSenha())){ //lembrar de pegar o perfil do usuários, se tiver doids
	 * levar para a pagina determinada session.setAttribute("usuarioLogado",
	 * usuario); return "redirect:/papeis/listar"; } } return
	 * "redirect:/papeis/novo"; }
	 */
	/*
	 * @RequestMapping(value = "/efetuarLogout") public String
	 * efetuarLogout(HttpSession session){ session.invalidate(); //Colocar para
	 * formulario inicial, com conteudo para não cadastrados return
	 * "redirect:formularioLogin"; }
	 */
}