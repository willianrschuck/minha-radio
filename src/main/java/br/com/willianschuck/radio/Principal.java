package br.com.willianschuck.radio;

import br.com.willianschuck.radio.dao.CidadeDAO;
import br.com.willianschuck.radio.dao.EnderecoDAO;
import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Endereco;

public class Principal {

	public static void main(String[] args) {

		CidadeDAO cidadeDAO = new CidadeDAO();
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		
		Endereco endereco = enderecoDAO.find(1);
		System.out.println(endereco.getCidade().getEstado().getNome());
		
	}	

}
