package br.com.willianschuck.radio.cliente;

import br.com.willianschuck.base.Validator;
import br.com.willianschuck.base.ValidatorUtil;
import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.endereco.EnderecoValidator;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.model.TipoPessoa;
import br.com.willianschuck.util.StringUtils;

public class ClienteValidator implements Validator<Cliente> {

	private Validator<Endereco> enderecoValidator = new EnderecoValidator();

	public void validate(Cliente c) throws InvalidValueException {
	
		if (c.getTipoPessoa() == TipoPessoa.FISICA) {
			ValidatorUtil.validate(c.getNomeFantasia(), "nome", false, 60, 3);
			ValidatorUtil.validate(c.getCpf(), "cpf", false, 11, 11);
		} else {
			ValidatorUtil.validate(c.getRazaoSocial(), "razão social", false, 60, 3);
			ValidatorUtil.validate(c.getCnpj(), "cnpj", false, 14, 14);
		}
		ValidatorUtil.validate(c.getTelefone(), "telefone", false);
		if (StringUtils.isNotBlank(c.getEmail())) {
			ValidatorUtil.Email.validate(c.getEmail());
		}
		enderecoValidator.validate(c.getEndereco());
		
	}

}
