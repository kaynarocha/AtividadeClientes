package br.com.senac.api_pessoa.services;

import br.com.senac.api_pessoa.dtos.ClientesRequestDTO;
import br.com.senac.api_pessoa.entidades.Clientes;
import br.com.senac.api_pessoa.repositorios.ClienteRepositorio;
import org.springframework.stereotype.Service;

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

    public List<Clientes> listar() {
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
        Clientes clientePersist =
                this.clientesResquestDtoParaClientes(cliente);
        clientePersist.setId(id);

        return clienteRepositorio.save(clientePersist);
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
}
