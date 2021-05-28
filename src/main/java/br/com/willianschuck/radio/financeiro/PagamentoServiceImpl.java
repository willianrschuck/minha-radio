package br.com.willianschuck.radio.financeiro;

import br.com.willianschuck.radio.model.Conta;
import br.com.willianschuck.radio.model.Pagamento;

public class PagamentoServiceImpl implements PagamentoService {

	@Override
	public void pagar(Conta conta) {
		Double valorPendente = getValorPendente(conta);
		if (valorPendente > 0) {
			conta.getPagamentos().add(new Pagamento(conta, valorPendente));
		} else {
			// TODO: Emitir erro
		}
	}

	@Override
	public void pagar(Conta conta, Double valor) {
		Double valorPendente = getValorPendente(conta);
		if (valorPendente <= valor) {
			conta.getPagamentos().add(new Pagamento(conta, valorPendente));
		} else {
			// TODO: Emitir erro
		}
	}
	
	private Double getValorPendente(Conta conta) {
		return conta.getValor() - conta.getValorPago();
	}

}
