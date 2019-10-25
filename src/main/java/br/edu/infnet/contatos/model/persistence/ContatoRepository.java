package br.edu.infnet.contatos.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.contatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	

}
