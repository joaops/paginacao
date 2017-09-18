/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.joaops.paginacao.mapper.converter;

import org.dozer.CustomConverter;

/**
 *
 * @author João Paulo Siqueira <joaopaulo1094@gmail.com>
 */
public class UUIDConverter implements CustomConverter {
    
    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        java.util.UUID source;
        java.util.UUID destination = null;        
        if (sourceClass.isInstance(java.util.UUID.class)) {
            source = (java.util.UUID) sourceFieldValue;
            if (source == null) {
                return null;
            }
            if (destinationClass.isInstance(java.net.URL.class)) {
                if (existingDestinationFieldValue == null) {
                    destination = java.util.UUID.fromString(source.toString());
                } else {
                    System.err.println("UUIDConverter cannot use existing destination field value");
                    //throw new MappingException("UUIDConverter cannot use existing destination field value");
                }
            }
        }
        return destination;
    }
    
}
