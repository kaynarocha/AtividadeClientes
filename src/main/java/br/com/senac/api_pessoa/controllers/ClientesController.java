package br.com.senac.api_pessoa.controllers;

import br.com.senac.api_pessoa.dtos.ClientesRequestDTO;
import br.com.senac.api_pessoa.entidades.Clientes;
import br.com.senac.api_pessoa.repositorios.ClienteRepositorio;
import br.com.senac.api_pessoa.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/clientes")
public class ClientesController {

    private ClienteService clienteService;

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Clientes>> listar() {
        return ResponseEntity
                .ok(this.clienteService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<Clientes> criar(
            @RequestBody ClientesRequestDTO clientes) {
        try {
            return ResponseEntity.ok(clienteService.criar(clientes));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }

    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Clientes> atualizar(
            @RequestBody ClientesRequestDTO clientes, @PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(clienteService.atualizar(id, clientes));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Clientes> deletar(
            @PathVariable Long id) {
        try {
            clienteService.deletar(id);
            return ResponseEntity.ok(null);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Clientes> listarById(
            @PathVariable Long id) {
        try {
            return ResponseEntity
                    .ok(clienteService.listarById(id));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(null);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(null);
        }
    }

    @GetMapping("/listar/{nome}")
}