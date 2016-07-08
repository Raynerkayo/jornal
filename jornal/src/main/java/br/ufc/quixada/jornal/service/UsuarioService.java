package br.ufc.quixada.jornal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.quixada.jornal.model.Papel;
import br.ufc.quixada.jornal.model.Usuario;
import br.ufc.quixada.jornal.repository.PapelRepository;
import br.ufc.quixada.jornal.repository.UsuarioRepository;
import br.ufc.quixada.jornal.securanca.Criptografia;

/**
 * @author rayner Classe de serviço, que deverá conter regras de negócio sempre
 *         que necessário.
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private Criptografia criptografar;

	// Lembrar de comparar os nomes dos usuários
	public void salvar(Usuario usuario) {
		usuario.setSenha(criptografar.criptografarSenha(usuario.getSenha()));
		usuarioRepository.save(usuario);
	}

	public void excluir(Long id) {
		usuarioRepository.delete(id);
	}

	public List<Usuario> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		return usuarios;
	}

	public List<Usuario> pesquisar(Usuario usuario) {
		String pesquisarUsuario = usuario.getNomeCompleto() == null ? "%" : usuario.getNomeCompleto();
		return usuarioRepository.findByNomeCompletoContaining(pesquisarUsuario);
	}

	public Usuario logar(String login) {
		// List<Papel> papeis = papelRepository.findByPapelNome(Long id);
		Usuario usuario = usuarioRepository.findByLoginLike(login);

		return usuario;
	}

	public Usuario logar(String login, String senha) {
		Usuario usuario = usuarioRepository.findByLoginLike(login);
		if (usuario != null) {
			if (criptografar.criptografarSenha(senha).equals(usuario.getSenha())) {
				Papel papel = papelRepository.findByPapelIdUsuario(usuario.getId());
				if(papel == null){
					return usuario;
				}else{
				usuario.setPapeis(papel);
				return usuario;
				}
			}
		}
		return null;
	}

	public Usuario buscarPorId(Long usuario) {
		return usuarioRepository.findByIdLike(usuario);
	}
}
