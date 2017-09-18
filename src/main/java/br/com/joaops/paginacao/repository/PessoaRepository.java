/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.paginacao.repository;

import br.com.joaops.paginacao.model.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author João Paulo
 */
@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {
    
}