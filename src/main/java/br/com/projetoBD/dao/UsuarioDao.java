package br.com.projetoBD.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.projetoBD.beans.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

	public Usuario findByEmailAndSenha(String email, String senha);
	
}
