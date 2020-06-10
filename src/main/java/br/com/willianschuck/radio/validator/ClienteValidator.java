package br.com.willianschuck.radio.validator;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.util.EmailUtils;
import br.com.willianschuck.util.StringUtils;

public class ClienteValidator {

	public void validate(Cliente cliente) throws InvalidValueException {
		
		if (StringUtils.isBlank(cliente.getNome())) {
			throw new InvalidValueException("O nome é obrigatório");
		} else if (StringUtils.isBlank(cliente.getTelefone())) {
			throw new InvalidValueException("O telefone é obrigatório");
		} else if (StringUtils.isNotBlank(cliente.getEmail())) {
			if (!EmailUtils.isValid(cliente.getEmail())) {
				throw new InvalidValueException("O email é inválido");
			}
		}
		
		if (cliente.getEndereco() == null) {
			throw new InvalidValueException("O endereço é obrigatório");
		}
		validate(cliente.getEndereco());
		
	}

	private void validate(Endereco endereco) throws InvalidValueException {
		
		if (endereco.getCidade().getId() == null) {
			throw new InvalidValueException("A cidade deve ser informada.");
		} else if (StringUtils.isBlank(endereco.getLogradouro())) {
			throw new InvalidValueException("O logradouro é obrigatório");
		} else if (StringUtils.isBlank(endereco.getBairro())) {
			throw new InvalidValueException("O bairro é obrigatório");
		} else if (StringUtils.isBlank(endereco.getNumero())) {
			throw new InvalidValueException("O número é obrigatório");
		}
		
	}

}
