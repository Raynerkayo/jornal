package br.ufc.quixada.jornal.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufc.quixada.jornal.model.Classificado;
import br.ufc.quixada.jornal.model.Oferta;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.ClassificadoService;
import br.ufc.quixada.jornal.service.OfertaService;

@Controller
@RequestMapping("/ofertas")
public class OfertaController {

	private static String CADASTRAR_OFERTA = "CadastrarOferta";

	@Autowired
	private OfertaService ofertaService;

	@Autowired
	private ClassificadoService classificadoService;

	@RequestMapping(value = "/nova", method = RequestMethod.GET)
	public String nova(@PathVariable("id") Long id, Model model) {
		model.addAttribute(new Oferta());
		return CADASTRAR_OFERTA;
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public String salvar(@Validated Oferta oferta, Long idClassificado, Model model, HttpSession session) {
		Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuarioLogado");
		
		Classificado classificado = classificadoService.buscarPorId(idClassificado);
		Oferta melhorOferta = ofertaService.listar(classificado);
		if (session != null) {
			if (melhorOferta == null) {
				if (oferta.getValorOferta() > classificado.getPreco()) {
					oferta.setClassificado(classificadoService.buscarPorId(idClassificado));
					oferta.setUsuario(usuarioDaSessao);
					oferta.setDataOferta(new Date());
					ofertaService.salvar(oferta);
					return "redirect:/classificados/listar";
				} else {
					return "PermissaoNegada";
				}
			} else {
					if (melhorOferta.getClassificado().getId().equals(idClassificado)) {
						if (melhorOferta.getValorOferta() >= oferta.getValorOferta()
								|| melhorOferta.getClassificado().getPreco() >= oferta.getValorOferta()) {
							return "PermissaoNegada";
						} else {
							oferta.setId(melhorOferta.getId());
							oferta.setClassificado(classificadoService.buscarPorId(idClassificado));
							oferta.setUsuario(usuarioDaSessao);
							oferta.setDataOferta(new Date());
							ofertaService.salvar(oferta);
							return "redirect:/classificados/listar";
						}
					}
				}
		}
		return "redirect:/classificados/listar";
	}

}