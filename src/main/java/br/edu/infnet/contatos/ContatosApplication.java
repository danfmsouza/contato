package br.edu.infnet.contatos;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.infnet.contatos.model.Contato;
import br.edu.infnet.contatos.model.persistence.ContatoRepository;

@SpringBootApplication
public class ContatosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContatosApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(ContatoRepository repository) {
        return args -> {
            repository.deleteAll();
            LongStream.range(1, 11)
                    .mapToObj(i -> {
                        Contato c = new Contato();
                        c.setNome("Contato " + i);
                        c.setEmail("contato" + i + "@email.com");
                        c.setCelular(999999999);
                        c.setEndereco("Rua dos Tabajaras 69");
                        c.setFixo(2135430299);
                        return c;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }

}
