package br.com.projetoBD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBD.beans.Usuario;
import br.com.projetoBD.dao.UsuarioDao;

@RestController
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioDao dao;
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<Usuario>> getAll(){
		
		List<Usuario> usuarios = (List<Usuario>)dao.findAll();
		
		if(usuarios.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(usuarios);
		
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable int id){
		Usuario usuario = dao.findById(id).orElse(null);
		
		if(usuario == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/novousuario")
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario){
		try {
			if(usuario.getId() != 0) {
				boolean exists = dao.existsById(usuario.getId());
				if(!exists) {
					return ResponseEntity.status(404).build();
				}
			}
			dao.save(usuario);
			
			return ResponseEntity.ok(usuario);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(403).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> logar(@RequestBody Usuario objeto){
		Usuario usuario = dao.findByEmailAndSenha(objeto.getEmail(), objeto.getSenha());
		
		if(usuario == null) {
			return ResponseEntity.status(404).build();
		}
		
		return ResponseEntity.ok(usuario);
		
	}
}
