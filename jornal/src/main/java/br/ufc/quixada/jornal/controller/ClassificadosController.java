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
import br.ufc.quixada.jornal.model.Oferta;
import br.ufc.quixada.jornal.service.ClassificadoService;
import br.ufc.quixada.jornal.service.OfertaService;

@Controller
@RequestMapping("/classificados")
public class ClassificadosController {

	private static String CADASTRAR_CLASSIFICADOS = "editor/CadastrarClassificados";
	private static String LISTAR_CLASSIFICADOS = "editor/ListarClassificados";

	@Autowired
	private ClassificadoService classificadoService;
	
	@Autowired
	private OfertaService ofertaService;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model, Classificado classificado) {
		model.addAttribute(new Classificado());
		return CADASTRAR_CLASSIFICADOS;
	} 

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Classificado classificado, Model model, RedirectAttributes attributes,
			HttpSession session) {
		// verificar se é um editor
		classificadoService.salvar(classificado);
		return "redirect:/editor/administracao";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarClassificados(Model model, Long idClassificado) {
		List<Classificado> classificados = classificadoService.listar();
		
		for(Classificado classificado : classificados){
			if(classificado.getId().equals(idClassificado)){
				Oferta melhorOferta = ofertaService.listar(classificado);
				classificado.setOferta(melhorOferta);
				model.addAttribute("classificados", classificados);
				model.addAttribute("ofertas", melhorOferta);
				return LISTAR_CLASSIFICADOS;
			}
		}
		
		model.addAttribute("classificados", classificadoService.listar());
		
		return LISTAR_CLASSIFICADOS;
	}
	
	

}
