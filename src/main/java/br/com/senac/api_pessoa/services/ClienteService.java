package br.com.senac.api_pessoa.services;

import br.com.senac.api_pessoa.dtos.ClienteFiltroDto;
import br.com.senac.api_pessoa.dtos.ClientesRequestDTO;
import br.com.senac.api_pessoa.entidades.Clientes;
import br.com.senac.api_pessoa.repositorios.ClienteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepositorio clienteRepositorio;
    // startar uma dependencia, e esse cara vai centralizar as regras de negocio
    // ele vai receber uma requisição e vai enviar
    // gerencia as trocas de informações entre controle e repositorio, vai processar e implementar os negocios
    // se der tudo certo, ir para devolver a camada e que sera ?????

    public ClienteService(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public List<Clientes> listar(ClienteFiltroDto filtro) {
        if (filtro.getNome() != null) {
            return clienteRepositorio
                    .findByNomeContaining(filtro.getNome());
        }

        if (filtro.getEmail() != null) {
            return clienteRepositorio
                    .findByEmail(filtro.getEmail());
        }

        if (filtro.getIdade() != null ) {
            return clienteRepositorio
                    .findByIdadeGreaterThan(filtro.getIdade());
        }
        return clienteRepositorio.findAll();

    }



    public Clientes criar(ClientesRequestDTO cliente) {
        Clientes clientePersist =
                this.clientesResquestDtoParaClientes(cliente);

        return clienteRepositorio.save(clientePersist);
    }

    public Clientes atualizar(
            Long id,
            ClientesRequestDTO cliente) {
        if (clienteRepositorio.existsById(id)) {
            Clientes clientePersist =
                    this.clientesResquestDtoParaClientes(cliente);
            clientePersist.setId(id);

            return clienteRepositorio.save(clientePersist);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

    public void deletar(Long id) {
        if (clienteRepositorio.existsById(id)) {
            clienteRepositorio.deleteById(id);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

    private Clientes clientesResquestDtoParaClientes(
            ClientesRequestDTO entrada) {

        Clientes saida = new Clientes();
        saida.setNome(entrada.getNome());
        saida.setDocumento(entrada.getDocumento());
        saida.setIdade(entrada.getIdade());
        saida.setEmail(entrada.getEmail());

        return saida;
    }

    public Clientes listarById(Long id) {
        if (clienteRepositorio.existsById(id)) {
            return clienteRepositorio
                    .findById(id)
                    .get();
        }
        throw new RuntimeException("Cliente nao encontrado");
    }


    }

