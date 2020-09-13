package br.com.willianschuck.radio.endereco;

import java.util.List;

import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Estado;

public class EnderecoServiceImpl implements EnderecoService {
	
	private EstadoDAO estadoDao = new EstadoDAO();
	private CidadeDAO cidadeDao = new CidadeDAO(); 

	public EnderecoServiceImpl() {
	}
	
	@Override
	public List<Estado> getEstados() {
		return estadoDao.getAll();
	}

	@Override
	public List<Cidade> getCidadesFrom(Estado estado) {
		return cidadeDao.getFrom(estado);
	}
	
}
