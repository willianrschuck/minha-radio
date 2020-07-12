package br.com.willianschuck.radio.service;

import br.com.willianschuck.radio.dao.EmissoraDAO;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.validator.EmissoraValidator;

public class EmissoraService extends Service<Emissora> {

	public EmissoraService() {
		super(new EmissoraDAO(), new EmissoraValidator());
	}

	@Override
	public Emissora create() {
		
		Emissora emissora = new Emissora();
		emissora.setEndereco(new Endereco());
		return emissora;
		
	}
}
