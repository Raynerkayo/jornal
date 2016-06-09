package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.repositoy.UsuarioRepository;

/**
 * @author rayner
 * Classe de serviço, que deverá conter regras de negócio sempre que necessário.
 */
@Service
public class CadastroUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void salvar(Usuario usuario){
		usuarioRepository.save(usuario);
	}
	
	public void excluir(Long id){
		usuarioRepository.delete(id);
	}
	
	public List<Usuario> listar(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}
	
	public List<Usuario> pesquisar(Usuario usuario){
		String pesquisarUsuario = usuario.getNomeCompleto() == null ? "%" : usuario.getNomeCompleto();
		return usuarioRepository.findByNomeCompletoContaining(pesquisarUsuario);
	}
	
}
