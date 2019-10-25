package br.edu.infnet.contatos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data 
@NoArgsConstructor @AllArgsConstructor 
@ToString(exclude="id")
@Entity 
	public class Contato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_do_contato")
	public String nome;
	
	private String email;
	private Integer celular;
	private Integer fixo;
	private String endereco;
}
