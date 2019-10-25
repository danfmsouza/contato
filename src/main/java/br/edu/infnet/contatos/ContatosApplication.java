package br.edu.infnet.contatos;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
                        Contact c = new Contact();
                        c.setName("Contact " + i);
                        c.setEmail("contact" + i + "@email.com");
                        c.setPhone("(111) 111-1111");
                        return c;
                    })
                    .map(v -> repository.save(v))
                    .forEach(System.out::println);
        };
    }

}
