package br.edu.infnet.contatos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.infnet.contatos.model.Contato;
import br.edu.infnet.contatos.model.persistence.ContatoRepository;

@RestController
@RequestMapping({"/contatos"})
public class ContatoController {
	private ContatoRepository repository;
	
	ContatoController(ContatoRepository contatoRepository) {
		this.repository = contatoRepository;
	}
	// CRUD
	@GetMapping
	public List findAll() {
		return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
				
	}
	@PostMapping
	public Contato create(@RequestBody Contato contato){
		return repository.save(contato);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, 
			@RequestBody Contato contato) {
		return repository.findById(id)
				.map(
				record -> {
					record.setNome(contato.getNome());
					record.setEmail(contato.getEmail());
					record.setCelular(contato.getCelular());
					record.setFixo(contato.getFixo());
					record.setEndereco(contato.getEndereco());
					Contato updated = repository.save(record);
					return ResponseEntity.ok().body(updated);
				  }).orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
	
}

