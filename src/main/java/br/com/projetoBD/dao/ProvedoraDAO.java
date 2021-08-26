package br.com.projetoBD.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.projetoBD.beans.Provedora;

public interface ProvedoraDAO extends CrudRepository<Provedora, Integer> {
	public List<Provedora> findByNomeLike(String nome);
	
	public List<Provedora> findByFundacaoBetween(Date inicio, Date fim);
	
}
