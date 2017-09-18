/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.paginacao.service;

import br.com.joaops.paginacao.dto.PessoaDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author João Paulo
 */
public interface PessoaService {
    
    public PessoaDto newPessoa();
    public PessoaDto save(PessoaDto pessoaDto);
    public PessoaDto findOne(Long id);
    public void delete(Long id);
    public void delete(PessoaDto pessoaDto);
    public List<PessoaDto> findAll();
    public Page<PessoaDto> findAll(Pageable p);
    
}