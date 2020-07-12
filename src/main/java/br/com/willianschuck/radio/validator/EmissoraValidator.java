package br.com.willianschuck.radio.validator;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.model.Endereco;

public class EmissoraValidator implements Validator<Emissora> {

	private Validator<Endereco> enderecoValidator = new EnderecoValidator();

	public void validate(Emissora e) throws InvalidValueException {
		
		ValidatorUtil.validate(e.getNomeFantasia(), "nome fantasia", false, 60, 3);
		ValidatorUtil.validate(e.getRazaoSocial(), "razão social", false, 60, 3);
		ValidatorUtil.validate(e.getCnpj(), "cnpj", false, 60, 3);
		
		enderecoValidator.validate(e.getEndereco());
		
	}

}
