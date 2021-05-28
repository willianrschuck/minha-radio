package br.com.willianschuck.radio.financeiro;

import java.util.List;

import br.com.willianschuck.base.CrudService;
import br.com.willianschuck.radio.model.Conta;

public interface ContaService extends CrudService<Conta> {

	List<Conta> getAll();
	
}
