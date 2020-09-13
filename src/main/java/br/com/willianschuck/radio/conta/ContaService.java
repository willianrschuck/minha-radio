package br.com.willianschuck.radio.conta;

import java.util.List;

import br.com.willianschuck.base.CrudService;
import br.com.willianschuck.radio.model.Conta;

public interface ContaService extends CrudService<Conta> {

	List<Conta> getAll();
	
}
