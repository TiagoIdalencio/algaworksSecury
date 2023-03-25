package com.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algafood.domain.exception.NegocioException;
import com.algafood.domain.model.Cidade;
import com.algafood.domain.model.Restaurante;
import com.algafood.domain.model.Usuario;
import com.algafood.domain.repository.UsuarioRepository;
import com.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@RequestBody @Valid Usuario usuario) {
		try {
			return cadastroUsuario.salvar(usuario);
		} catch (EstadoNaoEncontradoException  e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{usuarioId}")
	public Usuario atualizar(@PathVariable Long usuarioId, @RequestBody @Valid Usuario usuario) {		
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		BeanUtils.copyProperties(usuario, usuarioAtual,  "id");

		try {
			return cadastroUsuario.salvar(usuarioAtual);
		} catch (EstadoNaoEncontradoException  e) {
			throw new NegocioException(e.getMessage());
		}
	}
}
