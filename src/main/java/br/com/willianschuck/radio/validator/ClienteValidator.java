package br.com.willianschuck.radio.validator;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.util.StringUtils;

public class ClienteValidator implements Validator<Cliente> {

	private Validator<Endereco> enderecoValidator = new EnderecoValidator();

	public void validate(Cliente c) throws InvalidValueException {
	
		ValidatorUtil.validate(c.getNome(), "nome", false, 60, 3);
		ValidatorUtil.validate(c.getTelefone(), "telefone", false);
		if (StringUtils.isNotBlank(c.getEmail())) {
			ValidatorUtil.Email.validate(c.getEmail());
		}
		enderecoValidator.validate(c.getEndereco());
		
	}

}
