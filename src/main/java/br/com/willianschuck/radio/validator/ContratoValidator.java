package br.com.willianschuck.radio.validator;

import br.com.willianschuck.exception.InvalidValueException;
import br.com.willianschuck.radio.model.Contrato;

public class ContratoValidator implements Validator<Contrato> {

	public void validate(Contrato contrato) throws InvalidValueException {
		
		ValidatorUtil.validateRequired(contrato.getCliente().getId(), "cliente");
		ValidatorUtil.validateRequired(contrato.getEmissora().getId(), "emissora");
		ValidatorUtil.validateRange(contrato.getPrazo(), "prazo", 0, Double.MAX_VALUE);
		ValidatorUtil.validateDate(contrato.getDataInicio(), "data de in�cio", false);
		ValidatorUtil.validateDate(contrato.getDataFinal(), "data de t�rmino", false);
		ValidatorUtil.validateDateRange(contrato.getDataFinal(), "data de t�rmino", false, contrato.getDataInicio());
		ValidatorUtil.validateRange(contrato.getPrecoMensal(), "preco mensal", 0.01, Double.MAX_VALUE);

	}

}
