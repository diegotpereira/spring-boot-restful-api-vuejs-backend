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

    // Buscar Todos os Clientes
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

    // Buscar Clientes por Id
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> buscarClientePorId(@PathVariable("id") Long id) {
        try {
            Cliente cliente = clienteRepositorio.findById(id).get();

            if (cliente != null) {
                return new ResponseEntity<Object>(cliente, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // Atualizar Dados do Cliente 
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable("id")Long id, @RequestBody Cliente cliente) {
        try {
            cliente.setId(id);
            Cliente salvarCliente = clienteRepositorio.save(cliente);

            return new ResponseEntity<Object>(salvarCliente, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // Salvar Dados do Cliente
    @PostMapping("/clientes")
    public ResponseEntity<Object> criarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente salvarCliente = clienteRepositorio.save(cliente);

            return new ResponseEntity<Object>(salvarCliente, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // Deletar Dados do Cliente 
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<HttpStatus> deletarCliente(@PathVariable("id")Long id) {
        try {
            clienteRepositorio.deleteById(id);

            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }
}
