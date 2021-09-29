package br.com.java.springbootrestfulapivuejsfrontend.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.java.springbootrestfulapivuejsfrontend.entity.Cliente;

public interface ClienteRepositorio extends CrudRepository<Cliente, Long> {
    
}
