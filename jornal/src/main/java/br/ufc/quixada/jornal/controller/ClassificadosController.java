package br.ufc.quixada.jornal.controller;

import java.util.List;

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
	
	private static String CADASTRAR_CLASSIFICADOS = "classificado/CadastroClassificado";
	private static String LISTAR_CLASSIFICADOS = "classificado/ListaDeClassificados";
	private static String REDIRECT_LISTAR_CLASSIFICADOS = "redirect:classificados/todosClassificados"; 
	
	@Autowired
	private ClassificadoService classificadoService;
	
	@RequestMapping(value = "/novo")
	public String novo(Model model){
		model.addAttribute(new Classificado());
		return CADASTRAR_CLASSIFICADOS;
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Classificado classificado, Model model, RedirectAttributes attributes ,HttpSession session){
		//verificar se é um editor
		classificadoService.salvar(classificado);
		return REDIRECT_LISTAR_CLASSIFICADOS;
	}
	
	@RequestMapping(value = "/listarClassificados", method = RequestMethod.GET)
	public String listarClassificados(Model model){
		List<Classificado> classificados = classificadoService.listar();
		model.addAttribute("classificados", classificados);
		return LISTAR_CLASSIFICADOS;
	}
	
	
	
	
	@RequestMapping(value = "/comprar", method = RequestMethod.GET)
	public String listaClassificado(){
		return null;
	}
	
	//criar os que estão vendidos, em aberto.
}
