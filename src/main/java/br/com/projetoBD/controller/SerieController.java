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

import br.com.projetoBD.beans.Provedora;
import br.com.projetoBD.beans.Serie;
import br.com.projetoBD.dao.SerieDAO;

@RestController
@CrossOrigin("*")
public class SerieController {
	
	@Autowired
	private SerieDAO dao;
	
	
	@PostMapping("/novaserie")
	public ResponseEntity<Serie> add(@RequestBody Serie object) {
		try {
			dao.save(object);
			return ResponseEntity.ok(object);
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(403).build();
		}
		
	}
	
	
	@GetMapping("/series")
	public ResponseEntity<List<Serie>> getAll(){
		try {
			List<Serie> series = (List<Serie>)dao.findAll();
			if(series.size() == 0) {
				return ResponseEntity.status(404).build();
			}
			else {
				return ResponseEntity.ok(series);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(404).build();
		}
		
	}
	
	
	@GetMapping("/serie/{id}")
	public ResponseEntity<Serie> getSerie(@PathVariable int id) {
		Serie result = dao.findById(id).orElse(null);
		if(result == null) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.ok(result);
		}
		
	}
	
}
