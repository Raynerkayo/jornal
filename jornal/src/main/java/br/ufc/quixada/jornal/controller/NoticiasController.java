package br.ufc.quixada.jornal.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.ufc.quixada.jornal.media.FileUtil;
import br.ufc.quixada.jornal.model.Comentario;
import br.ufc.quixada.jornal.model.Noticia;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.service.ComentarioService;
import br.ufc.quixada.jornal.service.NoticiaService;
import br.ufc.quixada.jornal.service.SecaoService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {

	private static final String CADASTRAR_NOTICIAS = "noticias/CadastrarNoticias";
	private static final String LISTAR_NOTICIAS = "noticias/ListarNoticias";
	private static final String LISTAR_MANCHETES = "noticias/ListarManchetes";
	private static final String EDITOR = "editor";

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private NoticiaService noticiaService;

	@Autowired
	private SecaoService secaoService;

	@Autowired
	private ComentarioService comentarioService;

	@RequestMapping("/nova")
	public String novo(Model model, Noticia noticia, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("jornalista")) {
			model.addAttribute("secoes", secaoService.listar());
			model.addAttribute(new Noticia());
			return CADASTRAR_NOTICIAS;
		} else {
			return "PermissaoNegada";
		}
	}

	@RequestMapping(value = "/nova", method = RequestMethod.POST)
	public String salvar(Noticia noticia, Model model, HttpSession session,
			@RequestParam(value = "file", required = false) MultipartFile file) {

		Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuarioLogado");

		if (usuarioDaSessao == null) {
			return "PermissaoNegada";
		}
		if (usuarioDaSessao.getPapeis().get(0).getPapelNome().equalsIgnoreCase("JORNALISTA")) {
			noticia.setUsuario(usuarioDaSessao);
			if (file != null && !file.isEmpty()) {
				String path = servletContext.getRealPath("/") + "resources/imagens/" + noticia.getTitulo() + ".png";
				FileUtil.saveFile(path, file);
			}
			noticia.setDataNoticia(new Date());
			noticia.setSecao(secaoService.buscarSecaoId(noticia.getSecao().getId()));

			noticiaService.salvar(noticia);
			return "redirect:/noticias/listar";
		} else {
			return "PermissaoNegada";
		}
	}

	@RequestMapping("/minhas")
	public String listarMinhasNoticias(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		if (usuario.getPapeis().get(0).getPapelNome().equalsIgnoreCase("jornalista")) {
			model.addAttribute("noticias", noticiaService.listarNoticiaUsuario(usuario));
			return LISTAR_NOTICIAS;
		}else{
			return "PermissaoNegada";
		}
	}

	@RequestMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("noticias", noticiaService.listar());
		return LISTAR_NOTICIAS;
	}

	@RequestMapping("/listar/{id}")
	public String listarPorId(@PathVariable("id") Long id, Model model) {
		Noticia noticia = noticiaService.buscarNoticia(id);
		List<Comentario> comentarios = comentarioService.findByComentarioNoticia(noticia);
		noticia.setComentarios(comentarios);
		model.addAttribute("comentarios", comentarios);
		model.addAttribute("noticias", noticia);
		return LISTAR_NOTICIAS;
	}

	@RequestMapping("editar/{id}")
	public String editar(@PathVariable("id") Noticia noticia, Model model, HttpSession session) {
		Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuarioLogado");
		Noticia noticiaA = noticiaService.buscarNoticiaPorIdNoticia(noticia.getId());
		if (noticiaA.getUsuario().getId() == usuarioDaSessao.getId()) {
			model.addAttribute("secoes", secaoService.listar());
			model.addAttribute("noticia", noticia);
			return CADASTRAR_NOTICIAS;
		} else {
			return "PermissaoNegada";
		}
	}

	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id, HttpSession session) {
		Usuario usuarioDaSessao = (Usuario) session.getAttribute("usuarioLogado");
		Noticia noticia = noticiaService.buscarNoticiaPorIdNoticia(id);
		if (noticia.getUsuario().getId() == usuarioDaSessao.getId()
				|| usuarioDaSessao.getPapeis().get(0).getPapelNome().equalsIgnoreCase(EDITOR)) {
			noticiaService.excluir(id);
			return "redirect:/noticias/listar";
		}
		return "PermissaoNegada";
	}

	@RequestMapping(value = "/secao/{id}", method = RequestMethod.GET)
	public String listarNoticiasSecoes(@PathVariable("id") Long id, Model model) {
		model.addAttribute("noticias", noticiaService.listarNoticiaSecao(id));
		return LISTAR_MANCHETES;
	}

}