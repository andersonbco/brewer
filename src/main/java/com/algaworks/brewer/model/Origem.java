package com.algaworks.brewer.model;

public enum Origem {

	NACIONAL("Nacional"),
	INTERNACIONAL("Internacional");
	
	Origem(String descricao){
		this.descricao = descricao;
	}
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
}
