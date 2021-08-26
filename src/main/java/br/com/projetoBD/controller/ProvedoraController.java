package br.com.projetoBD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoBD.beans.FiltroProvedora;
import br.com.projetoBD.beans.Provedora;
import br.com.projetoBD.dao.ProvedoraDAO;

@RestController
@CrossOrigin("*")
public class ProvedoraController {
	@Autowired
	private ProvedoraDAO dao;
	
	@PostMapping("/novaprovedora")
	public ResponseEntity<Provedora> add(@RequestBody Provedora object) {
		try {
			dao.save(object);
			
			return ResponseEntity.ok(object);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(403).build();
		}
		
	}
	
	
	@GetMapping("/provedoras")
	public ResponseEntity<List<Provedora>> getAll(){
		List<Provedora> colecao = (List<Provedora>)dao.findAll();
		
		if(colecao.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		else {
			return ResponseEntity.ok(colecao);
				
		}
	}
	
	
	@GetMapping("/provedora/{id}")
	public ResponseEntity<Provedora> getProvedora(@PathVariable int id) {
		Provedora result = dao.findById(id).orElse(null);
		if(result == null) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.ok(result);
		}
		
	}
	
	@GetMapping("/provedoras/filter")
	public ResponseEntity<List<Provedora>> filter(@RequestParam(name="nome", required = false) String nome){
		List<Provedora> colecao = (List<Provedora>)dao.findByNomeLike("%" + nome + "%");
		
		if(colecao.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		else {
			return ResponseEntity.ok(colecao);
				
		}
	}
	
	@PostMapping("/provedoras/filter/search")
	public ResponseEntity<List<Provedora>> filter(@RequestBody FiltroProvedora filtro){
		
		System.out.println("datas: " + filtro.getDataFinal());
		List<Provedora> colecao = dao.findByFundacaoBetween(filtro.getDataInicial(), filtro.getDataFinal());
		
		if(colecao.size() == 0) {
			return ResponseEntity.status(404).build();
		}
		else {
			return ResponseEntity.ok(colecao);
				
		}
	}
	
	
	
	
}
