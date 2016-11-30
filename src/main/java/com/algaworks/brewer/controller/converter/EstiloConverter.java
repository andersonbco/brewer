package com.algaworks.brewer.controller.converter;

import org.springframework.core.convert.converter.Converter;

import com.algaworks.brewer.model.Estilo;

public class EstiloConverter implements Converter<String, Estilo>{

	@Override
	public Estilo convert(String source) {
		
		Estilo estilo = new Estilo();
		estilo.setCodigo(Long.valueOf(source));
		
		return estilo;
	}

}
