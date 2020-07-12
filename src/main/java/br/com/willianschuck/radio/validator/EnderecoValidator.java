package br.com.willianschuck.radio.validator;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.model.Endereco;

public class EnderecoValidator implements Validator<Endereco> {

	public void validate(Endereco e) throws InvalidValueException {
		
		if (e == null) {
			throw new InvalidValueException("O endereço é obrigatório");
		}
		if (e.getCidade().getId() == null) {
			throw new InvalidValueException("O campo cidade é orbrigatório.");
		}
		
		ValidatorUtil.validate(e.getLogradouro(), "logradouro", false, 60, 3);
		ValidatorUtil.validate(e.getBairro(), "bairro", false, 40, 3);
		ValidatorUtil.validate(e.getNumero(), "numero", false, 5, 1);
		
	}

}
