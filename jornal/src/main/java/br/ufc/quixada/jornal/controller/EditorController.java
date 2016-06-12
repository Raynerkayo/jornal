package br.ufc.quixada.jornal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.model.Papel;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.ClassificadoService;
import br.ufc.quixada.jornal.service.UsuarioService;

@Controller
@RequestMapping("/editor")
public class EditorController {

	@Autowired
	private UsuarioService serviceUsuario;

	@Autowired
	private ClassificadoService classificadoService;

	private static String CADASTRAR_JORNALISTA = "editor/CadastrarJornalista";
	private static String CADASTRAR_CLASSIFICADO = "editor/CadastrarClassificado";
	private static Long ID_JORNALISTA = 2L;

	@RequestMapping(value = "/cadastrar/jornalista", method = RequestMethod.GET)
	public String novoJornalista(Usuario usuario, Model model) {
		// model.addAttribute("jornalista", new Usuario());
		model.addAttribute(new Usuario());
		return CADASTRAR_JORNALISTA;
	}

	@RequestMapping(value = "/cadastrar/jornalista", method = RequestMethod.POST)
	public String cadastarJornalista(@Validated Usuario usuario, Papel papel, Model model) {
		papel.setId(ID_JORNALISTA);
		usuario.setPapeis(papel);
		serviceUsuario.salvar(usuario);
		return "redirect:/editor/cadastrar/jornalista";
	}

	@RequestMapping(value = "/cadastrar/classificado", method = RequestMethod.GET)
	public String novoClassificado(Classificado classificado, Model model) {
		model.addAttribute(new Classificado());
		return CADASTRAR_CLASSIFICADO;
	}

	@RequestMapping(value = "/cadastrar/classificado", method = RequestMethod.POST)
	public String cadastrarClassificado(@Validated Classificado classificado,
			Model model) {

		classificadoService.salvar(classificado);
		return "redirect:/editor/cadastrar/classificado";
	}

}
