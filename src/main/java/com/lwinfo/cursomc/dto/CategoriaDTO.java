package com.lwinfo.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lwinfo.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;		
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min=5, max=60, message = "O tamanho deve ser entre 5 e 60 caracteres!")
	private String nome;
	
	public CategoriaDTO() {		
	}
	
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}