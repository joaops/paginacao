package br.com.joaops.paginacao.mapper;

import br.com.joaops.paginacao.dto.PessoaDto;
import br.com.joaops.paginacao.model.Pessoa;
import org.dozer.loader.api.BeanMappingBuilder;

/**
 *
 * @author João Paulo
 */
public class PessoaMapper extends BeanMappingBuilder {
    
    @Override
    protected void configure() {
        this.mapping(Pessoa.class, PessoaDto.class);
    }
    
}