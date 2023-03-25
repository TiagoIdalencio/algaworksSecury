package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario salvar(Usuario usuario) {	
		Usuario usuarioVelho = buscarOuFalhar(usuario.getId());
		
		if (!passwordEncoder.matches(usuarioVelho.getSenha(), usuario.getSenha())) {
	        throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
	    }
		
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		
		return usuarioRepository.save(usuario);
	}
		
	public Usuario buscarOuFalhar(Long usuarioId) {
		try {
			return usuarioRepository.findById(usuarioId).get();
		} catch (Exception ex) {		
			return null;
		}
	}
}
