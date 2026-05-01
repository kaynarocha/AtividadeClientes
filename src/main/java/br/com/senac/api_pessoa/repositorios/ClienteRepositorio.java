package br.com.senac.api_pessoa.repositorios;

import br.com.senac.api_pessoa.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Clientes, Long> {
    List<Clientes> findByNomeContaining(String nome);

    List<Clientes> findByEmail(String email);

    List<Clientes> findByIdadeGreaterThan(Integer idade);
}
