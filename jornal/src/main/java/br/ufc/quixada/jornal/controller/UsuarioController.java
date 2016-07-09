package br.ufc.quixada.jornal.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.PapelService;
import br.ufc.quixada.jornal.service.UsuarioService;
import br.ufc.quixada.util.UploadUtil;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final String CADASTRO_USUARIO = "usuario/CadastroUsuario";
	private static final String LISTAR_USUARIOS = "usuario/ListarUsuarios";
	private static final String PERMISSAO_NEGADA = "PermissaoNegada";

	@Autowired
	private UsuarioService cadastroUsuarioService;

	@Autowired
	private PapelService papelService;

	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Usuario usuario, Model model) {
		model.addAttribute("papeis", papelService.listar());
		model.addAttribute(new Usuario());
		return CADASTRO_USUARIO;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes, HttpSession session,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		if (errors.hasErrors()) {
			return CADASTRO_USUARIO;
		}
		/*
		 * Lembrar de verificar se já existe um login com o novo login
		 */

		Usuario usuarioSessao = (Usuario) session.getAttribute("usuarioLogado");
		if (image != null && !image.isEmpty()) {
			String path = servletContext.getContextPath().concat("/") + "resources/images/" + usuario.getLogin()
					+ ".png";
			UploadUtil.saveFile(path, image);
		}
		if (usuarioSessao == null) {
			if (usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("leitor")) {
				cadastroUsuarioService.salvar(usuario);
				System.out.println(
						"Salvou " + usuario.getNomeCompleto() + " que é " + usuario.getPapeis().get(0).getPapelNome());
				return "redirect:/usuarios/novo";
			} else {
				return "PermissaoNegada";
			}
		} else {
			if (usuarioSessao.getPapeis().get(0).getPapelNome().equalsIgnoreCase("EDITOR")) {
				cadastroUsuarioService.salvar(usuario);
				attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso.");
				return "redirect:/editor/administracao";
			} else {
				return PERMISSAO_NEGADA;
			}
		}

	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		List<Usuario> usuarios = cadastroUsuarioService.listar();
		model.addAttribute("users", usuarios);
		return LISTAR_USUARIOS;
	}

}