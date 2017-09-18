package br.com.joaops.paginacao.service.impl;

import br.com.joaops.paginacao.dto.PessoaDto;
import br.com.joaops.paginacao.model.Pessoa;
import br.com.joaops.paginacao.service.PessoaService;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.joaops.paginacao.repository.PessoaRepository;
import java.util.ArrayList;
import org.springframework.data.domain.PageImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author João Paulo
 */
@Service("PessoaService")
public class PessoaServiceImpl implements PessoaService {
    
    @Autowired
    private Mapper mapper;
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Override
    public PessoaDto newPessoa() {
        PessoaDto pessoaDto = new PessoaDto();
        return pessoaDto;
    }
    
    @Override
    public PessoaDto save(PessoaDto pessoaDto) {
        Pessoa pessoa = null;
        if (pessoaDto != null) {
            pessoa = new Pessoa();
            mapper.map(pessoaDto, pessoa);
            pessoa = pessoaRepository.save(pessoa);
        }
        PessoaDto retorno = null;
        if (pessoa != null) {
            retorno = new PessoaDto();
            mapper.map(pessoa, retorno);
        }
        return retorno;
    }
    
    @Transactional(readOnly = true)
    @Override
    public PessoaDto findOne(Long id) {
        Pessoa pessoa = pessoaRepository.findOne(id);
        PessoaDto retorno = null;
        if (pessoa != null) {
            retorno = new PessoaDto();
            mapper.map(pessoa, retorno);
        }
        return retorno;
    }
    
    @Override
    public void delete(Long id) {
        pessoaRepository.delete(id);
    }
    
    @Override
    public void delete(PessoaDto pessoaDto) {
        if (pessoaDto != null) {
            Pessoa pessoa = new Pessoa();
            mapper.map(pessoaDto, pessoa);
            pessoaRepository.delete(pessoa);
        }
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<PessoaDto> findAll() {
        Iterable<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaDto> dtos = new ArrayList<>();
        pessoas.forEach(pessoa -> {
            PessoaDto dto = new PessoaDto();
            mapper.map(pessoa, dto);
            dtos.add(dto);
        });
        return dtos;
    }
    
    @Transactional(readOnly = true)
    @Override
    public Page<PessoaDto> findAll(Pageable p) {
        List<PessoaDto> listDto = new ArrayList<>();
        Page<Pessoa> list = pessoaRepository.findAll(p);
        for (Pessoa pessoa : list) {
            PessoaDto pessoaDto = new PessoaDto();
            mapper.map(pessoa, pessoaDto);
            listDto.add(pessoaDto);
        }
        Page<PessoaDto> page;
        if (!listDto.isEmpty()) {
            page = new PageImpl<>(listDto, p, list.getTotalElements());
        } else {
            page = new PageImpl<>(new ArrayList<>(), p, 0);
        }
        return page;
    }
    
}