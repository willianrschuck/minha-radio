package br.com.willianschuck.radio.financeiro;

import br.com.willianschuck.radio.model.Conta;

public interface PagamentoService {

	void pagar(Conta conta);
	
	void pagar(Conta conta, Double valor);
	
}
