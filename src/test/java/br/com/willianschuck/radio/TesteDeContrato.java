package br.com.willianschuck.radio;

import java.util.Date;

import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;

public class TesteDeContrato {

	public static void main(String[] args) {
		
		Cliente mercadoGaucho = new Cliente();
		mercadoGaucho.setNome("Mercado Gaúcho");
		mercadoGaucho.setEmail("mercadogaucho@gmail.com");
		
		Emissora radio = new Emissora();
		radio.setNomeFantasia("Rádio Coqueiros 104.9fm");
		radio.setRazaoSocial("Rádio Coqueiros 104.9fm");
		radio.setCnpj("123456");
		
		Contrato contrato = new Contrato();
		contrato.setCliente(mercadoGaucho);
		contrato.setEmissora(radio);
		contrato.setDataInicio(new Date());
		
		System.out.println(contrato.getCliente().getNome());
		
	}
	
}
