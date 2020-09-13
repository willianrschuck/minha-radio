package br.com.willianschuck.radio.endereco;

import java.util.List;

import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Estado;

public interface EnderecoService {

	List<Estado> getEstados();

	List<Cidade> getCidadesFrom(Estado estado);

}
