package br.com.senac.api_pessoa.repositorios;

import br.com.senac.api_pessoa.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Clientes, Long> {
}
