package br.com.senac.api_pessoa.controllers;

import br.com.senac.api_pessoa.dtos.ClientesRequestDTO;
import br.com.senac.api_pessoa.entidades.Clientes;
import br.com.senac.api_pessoa.repositorios.ClienteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/clientes")
public class ClientesController {

    private ClienteRepositorio ClienteRepositorio;

    public ClientesController(ClienteRepositorio ClienteRepositorio) {
        this.ClienteRepositorio = ClienteRepositorio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar () {
        List<Clientes> clientesList = ClienteRepositorio.findAll();
        if (clientesList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(clientesList);
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar (
            @RequestBody ClientesRequestDTO clientes) {

        Clientes clientePersist = new Clientes();
        clientePersist.setNome(clientes.getNome());
        clientePersist.setSobrenome(clientes.getSobrenome());
        clientePersist.setEmail(clientes.getEmail());
        clientePersist.setIdade(clientes.getIdade());
        clientePersist.setDocumento(clientes.getDocumento());

        Clientes retorno =  ClienteRepositorio.save(clientePersist);

        return ResponseEntity.status(201).body(retorno);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar (
            @RequestBody ClientesRequestDTO clientes, @PathVariable Long id) {

        if (ClienteRepositorio.existsById(id)) {
            Clientes clientePersist = new Clientes();
            clientePersist.setNome(clientes.getNome());
            clientePersist.setSobrenome(clientes.getSobrenome());
            clientePersist.setEmail(clientes.getEmail());
            clientePersist.setIdade(clientes.getIdade());
            clientePersist.setDocumento(clientes.getDocumento());
            clientePersist.setId(id);

            Clientes retorno = ClienteRepositorio.save(clientePersist);

            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Clientes> deletar (
            @PathVariable Long id) {
        if (ClienteRepositorio.existsById(id)) {
            ClienteRepositorio.deleteById(id);

            return ResponseEntity.ok(null);
        }
        return ResponseEntity.badRequest().body(null);
    }

}