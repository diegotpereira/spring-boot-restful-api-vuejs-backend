package br.com.java.springbootrestfulapivuejsfrontend.controller;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.java.springbootrestfulapivuejsfrontend.entity.Cliente;
import br.com.java.springbootrestfulapivuejsfrontend.repository.ClienteRepositorio;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClienteControle {
    private Logger logger = LoggerFactory.getLogger(ClienteControle.class);

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("/clientes")
    public ResponseEntity<Object> buscarTodosClientes() {
        try {
            Iterable<Cliente> clientes = clienteRepositorio.findAll();

            return new ResponseEntity<Object>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
