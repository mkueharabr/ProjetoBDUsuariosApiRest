package br.com.projetoBD.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.projetoBD.beans.Serie;

public interface SerieDAO extends CrudRepository<Serie, Integer> {

}
