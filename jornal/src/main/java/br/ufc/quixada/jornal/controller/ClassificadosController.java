package br.ufc.quixada.jornal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.service.ClassificadoService;

@Controller
@RequestMapping("/classificados")
public class ClassificadosController {

	private static String CADASTRAR_CLASSIFICADOS = "editor/CadastrarClassificado";
	private static String LISTAR_CLASSIFICADOS = "editor/CadastrarClassificado";

	@Autowired
	private ClassificadoService classificadoService;

	@RequestMapping(value = "/novo")
	public String novo(Model model, Classificado classificado) {
		model.addAttribute(new Classificado());
		return CADASTRAR_CLASSIFICADOS;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Classificado classificado, Model model, RedirectAttributes attributes,
			HttpSession session) {
		// verificar se é um editor
		classificadoService.salvar(classificado);
		return "redirect:/classificados/novo";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarClassificados(Model model) {
		model.addAttribute("classificados", classificadoService.listar());
		return LISTAR_CLASSIFICADOS;
	}

	@RequestMapping(value = "{id}/comprar", method = RequestMethod.GET)
	public String listaClassificado() {
		return null;
	}
}
