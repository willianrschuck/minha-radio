package br.com.willianschuck.radio.service;

import static br.com.willianschuck.util.DateUtil.formatDate;
import static br.com.willianschuck.util.NumberUtil.formatInteger;

import java.util.Date;

import br.com.willianschuck.radio.dao.ContratoDAO;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.to.ContratoTO;
import br.com.willianschuck.radio.validator.ContratoValidator;

public class ContratoService extends Service<Contrato> {

	public ContratoService() {
		super(new ContratoDAO(), new ContratoValidator());
	}
	
	public Contrato create() {
		
		Contrato contrato = new Contrato();
		contrato.setDataInicio(new Date());
		return contrato;
		
	}
	
	public ContratoTO generateContratoTO(Contrato c) {
		
		Cliente cliente = c.getCliente();
		Emissora emissora = c.getEmissora();
		
		ContratoTO to = new ContratoTO();
		
		to.setBairroCliente(cliente.getEndereco().getBairro());
		to.setRazaoSocialCliente(cliente.getNome());
		to.setNomeFantasiaCliente(cliente.getNome());
		to.setRamoAtividadeCliente(" -- sem dados --");
		to.setEnderecoCliente(cliente.getEndereco().getLogradouro());
		to.setNumeroCliente(cliente.getEndereco().getNumero());
		to.setBairroCliente(cliente.getEndereco().getBairro());
		to.setCnpjCliente(" -- sem dados --");
		to.setInscricaoEstadualCliente(" -- sem dados --");
		to.setTipoAnuncio(" -- sem dados --");
		to.setDuracao("0");
		to.setPrazo(formatInteger(c.getPrazo()));
		to.setInicio(formatDate(c.getDataInicio()));
		to.setTermino(formatDate(c.getDataFinal()));
		to.setPrecoMensal(formatInteger(c.getPrecoMensal()));
		to.setPrecoTotal(" -- sem dados --");
		to.setDiscriminacaoInsercoes(" -- sem dados --");
		to.setFaturamento(" -- sem dados --");
		to.setVencimento(" -- sem dados --");
		to.setNomeEmissora(emissora.getRazaoSocial());
		to.setBairroEmissora(emissora.getEndereco().getBairro());
		to.setLogradouroEmissora(emissora.getEndereco().getLogradouro());
		to.setNumeroEmissora(emissora.getEndereco().getNumero());
		to.setCidadeEmissora(emissora.getEndereco().getCidade().getNome());
		to.setEstadoEmissora(emissora.getEndereco().getCidade().getEstado().getNome());
		
		return to;
	}
	
}
