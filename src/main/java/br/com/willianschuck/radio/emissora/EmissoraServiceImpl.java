package br.com.willianschuck.radio.emissora;

import br.com.willianschuck.base.AbstractCrudService;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.model.Endereco;

public class EmissoraServiceImpl extends AbstractCrudService<Emissora> {

	public EmissoraServiceImpl() {
		super(new EmissoraDAO());
	}

	@Override
	public Emissora create() {
		
		Emissora emissora = new Emissora();
		emissora.setEndereco(new Endereco());
		return emissora;
		
	}
}
