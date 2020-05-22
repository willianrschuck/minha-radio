package br.com.willianschuck.radio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.willianschuck.radio.model.Cidade;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;
import br.com.willianschuck.radio.model.Endereco;
import br.com.willianschuck.radio.model.Estado;
import br.com.willianschuck.radio.report.PrintContrato;

public class TesteDeImpressao {

	public static void main(String[] args) throws IOException {
		
		Cliente mercadoGaucho = new Cliente();
		mercadoGaucho.setNome("Mercado Gaúcho");
		mercadoGaucho.setEmail("mercadogaucho@gmail.com");
		
		Emissora radio = new Emissora();
		radio.setNomeFantasia("Rádio Coqueiros 104.9fm");
		radio.setRazaoSocial("Rádio Coqueiros 104.9fm");
		radio.setCnpj("123456");
		
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		
		Cidade cidade = new Cidade();
		cidade.setNome("Coqueiros do Sul");
		cidade.setEstado(estado);
		
		Endereco endereco = new Endereco();
		endereco.setBairro("Centro");
		endereco.setCidade(cidade);
		endereco.setLogradouro("Rua tal");
		endereco.setNumero("123");
		
		radio.setEndereco(endereco);
		
		mercadoGaucho.setEndereco(endereco);
		
		Contrato contrato = new Contrato();
		contrato.setCliente(mercadoGaucho);
		contrato.setRadio(radio);
		contrato.setDataInicio(new Date());
		
		
		

		SimpleDateFormat sdf = new SimpleDateFormat("hh-mm-ss_dd-MM-yyyy");
		String dest = sdf.format(new Date()) + ".pdf";
		
		byte[] generate = new PrintContrato().generate(contrato);
		
		OutputStream os = new FileOutputStream("./"+dest);
		os.write(generate);
		os.close();		
		
	}
	
}
