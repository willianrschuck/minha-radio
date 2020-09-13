package br.com.willianschuck.radio.contrato;

import static br.com.willianschuck.util.DateUtil.formatDate;
import static br.com.willianschuck.util.NumberUtil.formatCurrency;
import static br.com.willianschuck.util.NumberUtil.formatInteger;
import static br.com.willianschuck.util.PessoaUtil.formatCnpj;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderCollapsePropertyValue;
import com.itextpdf.layout.property.ListNumberingType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import br.com.willianschuck.base.ReportService;
import br.com.willianschuck.document.DocumentBuilder;
import br.com.willianschuck.radio.model.Cliente;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Emissora;

public class ContratoReportServiceImpl implements ReportService<Contrato> {
	
	private static PdfFont buildBaseFont() {
		try {
			return PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
		} catch (IOException e) {
			return null;
		}
	}
	
	private static PdfFont buildBaseFontBold() {
		try {
			return PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
		} catch (IOException e) {
			return null;
		}
	}
	
	private static DateFormat datePorExtenso = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("pt", "br"));
	
	private static String initTerms = "A %s, situada na %s, %s � %s, nesta cidade de %s, estado de %s, inscrita no CNPJ sob n� %s, doravante denominada CONTRATADA, compromete-se a veicular an�ncios institucionais, na forma de APOIO CULTURAL, nas seguintes condi��es:";
	private static String clausulaPrimeira = "O presente contrato somente entrar� em vigor ap�s a sua aprova��o pela Dire��o Geral da CONTRATADA.";
	private static String clausulaSegunda = "O cliente doravante denominado de CONTRATANTE, autoriza a emiss�o das faturas e respectivas duplicatas, que poder�o ser negociadas atrav�s de bancos ou cobradores credenciados pela CONTRTADA.";
	private static String clausulaTerceira = "Todas as altera��es a serem introduzidas neste contrato, bem como todos os cancelamentos, por parte do contratante, dever�o ser obrigatoriamente efetuados por escrito, com anteced�ncia de 72 (setenta e duas) horas.";
	private static String clausulaQuarta = "O valor a ser faturado e desde j� autorizado, corresponder� �s somas da inser��es transmitidas pela emissora at� o limite das verbas consignadas neste contrato, correspondendo, d�vida l�quida e certa pass�vel de execu��o judicial ou extra judicial, independente de aceite das duplicatas emitidas em fun��o deste contrato.";
	private static String clausulaQuinta = "O atraso nos pagamentos das duplicatas emitidas em decorr�ncia do presente contrato, acarretar� a suspens�o autom�tica dos primeiros an�ncios, podendo a CONTRATADA, por esse motivo, considerar rescindido o presente contrato.";
	private static String clausulaSexta = "O CONTRATANTE poder� fazer modifica��es na mensagem que estiver sendo veiculada, desde que avise � CONTRATADA com anteced�ncia de 72 (setenta e duas) horas, que ter� aprova��o da Diretoria.";
	private static String clausulaSetima = "O CONTRATANTE, poder� usufluir os benef�cios da Lei 8.313 de 23/12/91, Lei de Incentivo � Cultura.";
	private static String clausulaOitava = "A CONTRATADA ceder� � CONTRATANTE, � t�tulo de cortesia, seus t�cnicos, studios e equipamento para grava��o da mensagem a ser veiculada. Com exce��o ao trabalho de locu��o, que caso exista alguma prefer�ncia do contratante.";
	private static String clausulaNona = "presente contrato poder� ser rescindido por qualquer uma das partes, a qualquer tempo, sem que haja penalidades, obsevando-se apenas a comunica��o com 72 (setenta e duas) horas de anteced�ncia.";
	private static String clausulaDecima = "As partes elegem o foro de Carazinho, para dirimir as d�vidas oriundas do presente contrato, excluindo qualquer outro por mais privilegiado que seja.";

	private ContratoReportTO contrato;
	private PdfFont baseFont;
	private PdfFont baseFontBold;

	public byte[] makeReport(Contrato contrato) {
		return generate(getContratoTO(contrato));
	};
	
	private ContratoReportTO getContratoTO(Contrato c) {
		
		Cliente cliente = c.getCliente();
		Emissora emissora = c.getEmissora();
		
		ContratoReportTO to = new ContratoReportTO();
		
		to.setBairroCliente(cliente.getEndereco().getBairro());
		to.setRazaoSocialCliente(cliente.getRazaoSocial());
		to.setNomeFantasiaCliente(cliente.getNomeFantasia());
		to.setRamoAtividadeCliente(" -- sem dados --");
		to.setEnderecoCliente(cliente.getEndereco().getLogradouro());
		to.setNumeroCliente(cliente.getEndereco().getNumero());
		to.setBairroCliente(cliente.getEndereco().getBairro());
		to.setCnpjCliente(" -- sem dados --");
		to.setInscricaoEstadualCliente(" -- sem dados --");
		to.setTipoAnuncio(" -- sem dados --");
		to.setDuracao("30 segundo(s)");
		to.setPrazo(formatInteger(c.getPrazo()) + " meses");
		to.setDataInicio(c.getDataInicio());
		to.setTermino(formatDate(c.getDataFinal()));
		to.setPrecoMensal("R$ " + formatCurrency(c.getPrecoMensal()));
		to.setPrecoTotal("R$ " + formatCurrency(c.getValorTotal()));
		to.setDiscriminacaoInsercoes(c.getDiscriminacaoInsercoes());
		to.setFaturamento(" -- sem dados --");
		to.setVencimento(" -- sem dados --");
		to.setNomeEmissora(emissora.getRazaoSocial());
		to.setCnpjEmissora(formatCnpj(emissora.getCnpj()));
		to.setBairroEmissora(emissora.getEndereco().getBairro());
		to.setLogradouroEmissora(emissora.getEndereco().getLogradouro());
		to.setNumeroEmissora(emissora.getEndereco().getNumero());
		to.setCidadeEmissora(emissora.getEndereco().getCidade().getNome());
		to.setEstadoEmissora(emissora.getEndereco().getCidade().getEstado().getNome());
		
		return to;
	}
	
	public byte[] generate(ContratoReportTO c) {
		
		this.contrato = c;
		this.baseFont = buildBaseFont();
		this.baseFontBold = buildBaseFontBold();
		
		DocumentBuilder db = new DocumentBuilder();
		db.setBaseFont(baseFont);
		db.setBaseFontBold(baseFontBold);
		db.setBaseStyle(new Style().setMargin(0).setPadding(0).setFontSize(11).setBorder(Border.NO_BORDER).setFont(baseFont).setTextAlignment(TextAlignment.JUSTIFIED));
		
        Table docTable = db.createTable(1);
        
        Table header = buildHeader(db);
        Table infoTable = buildInfoTable(db);
        Table termsTable = buildTermsTable(db);
        Table authTable = buildAuthTable(db);
        
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(header));
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(infoTable));
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(termsTable));
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(authTable));
		
		db.addToDocument(docTable);
		
		
		return db.toByteArray();
		
	}

	private Table buildHeader(DocumentBuilder db) {
		
		Table table = db.createTable(12);
		
		table.setHeight(20f);
		
		table.addCell(db.createParagraphCell(12, contrato.getNomeEmissora(), new Style().setFontSize(12).setFont(baseFontBold).setTextAlignment(TextAlignment.CENTER)));
		
		return table;
	}

	private Table buildTermsTable(DocumentBuilder db) {

		Style style = new Style().setFontSize(10);
		
		Table table = db.createTable(12).setMarginTop(20);

		String terms = String.format(initTerms, contrato.getNomeEmissora(), contrato.getLogradouroEmissora(), contrato.getNumeroEmissora(), contrato.getBairroEmissora(), contrato.getCidadeEmissora(), contrato.getEstadoEmissora(), contrato.getCnpjEmissora());
		
        table.addCell(db.createParagraphCell(12, terms));
 
        List list = new List(ListNumberingType.DECIMAL).setFont(baseFont).setFontSize(10);
        list.add(db.createListItem(clausulaPrimeira, style));
        list.add(db.createListItem(clausulaSegunda, style));
        list.add(db.createListItem(clausulaTerceira, style));
        list.add(db.createListItem(clausulaQuarta, style));
        list.add(db.createListItem(clausulaQuinta, style));
        list.add(db.createListItem(clausulaSexta, style));
        list.add(db.createListItem(clausulaSetima, style));
        list.add(db.createListItem(clausulaOitava, style));
        list.add(db.createListItem(clausulaNona, style));
        list.add(db.createListItem(clausulaDecima, style));
        
        table.addCell(db.createEmptyCell(1, 12));
        table.addCell(db.createCell(1, 12).add(list));
        table.addCell(db.createParagraphCell(12, String.format("%s, %s.", contrato.getCidadeEmissora(), datePorExtenso.format(contrato.getDataInicio())), new Style().setTextAlignment(TextAlignment.CENTER).setPaddings(15, 0, 5, 0)));
        
		return table;
		
	}

	private Table buildInfoTable(DocumentBuilder db) {
		
		Table infoTable = db.createTable(12);
        
        Style titleStyle = new Style().setFont(baseFontBold).setFontSize(11).setTextAlignment(TextAlignment.CENTER);
        Style subtitleStyle = new Style().setFont(baseFont).setFontSize(10).setTextAlignment(TextAlignment.CENTER);
		infoTable.addCell(db.createParagraphCell(12, "CONTRATO DE APOIO CULTURAL", titleStyle));
        infoTable.addCell(db.createParagraphCell(12, "Lei n� 8313 de 23/12/91", subtitleStyle));
        
        infoTable.addCell(db.createEmptyCell(1, 12));

        infoTable.addCell(db.createLabelCell(12, "Raz�o Social: ", contrato.getRazaoSocialCliente()));
        
        infoTable.addCell(db.createLabelCell(6, "Nome Fantasia: ", contrato.getNomeFantasiaCliente()));
        infoTable.addCell(db.createLabelCell(6, "Ramo de Atividade: ", contrato.getRamoAtividadeCliente()));
        
        infoTable.addCell(db.createLabelCell(6, "Endere�o: ", contrato.getEnderecoCliente()));
        infoTable.addCell(db.createLabelCell(2, "N�: ", contrato.getNumeroCliente()));
        infoTable.addCell(db.createLabelCell(4, "Bairro: ", contrato.getBairroCliente()));
        
        infoTable.addCell(db.createLabelCell(6, "CNPJ: ", contrato.getCnpjCliente()));
        infoTable.addCell(db.createLabelCell(6, "Inscric�o Estadual: ", contrato.getInscricaoEstadualCliente()));
        
        infoTable.addCell(db.createLabelCell(6, "Tipo de An�ncio: ", contrato.getTipoAnuncio()));
        infoTable.addCell(db.createLabelCell(6, "Dura��o: ", contrato.getDuracao()));

        infoTable.addCell(db.createLabelCell(6, "Prazo deste Contrato: ", contrato.getPrazo()));
        infoTable.addCell(db.createLabelCell(3, "In�cio: ", formatDate(contrato.getDataInicio())));
        infoTable.addCell(db.createLabelCell(3, "T�rmino: ", contrato.getTermino()));

        infoTable.addCell(db.createLabelCell(6, "Pre�o Mensal: ", contrato.getPrecoMensal()));
        infoTable.addCell(db.createLabelCell(6, "Pre�o Total: ", contrato.getPrecoTotal()));
        
        Paragraph paragraph = db.createParagraph("Discrimina��o das Inser��es no Programa: ").setFont(baseFontBold).setTextAlignment(TextAlignment.LEFT)
        	.add(db.createParagraph(contrato.getDiscriminacaoInsercoes()));
        
        infoTable.addCell(db.createCell(1,  12).add(paragraph));
        
        return infoTable;
        
	}
	
	private Table buildAuthTable(DocumentBuilder db) {
		
		Table table = new Table(UnitValue.createPercentArray(2)).setBorder(Border.NO_BORDER).useAllAvailableWidth();
		
		int fontSize = 9;
        
        table.setBorderCollapse(BorderCollapsePropertyValue.SEPARATE);
        table.setHorizontalBorderSpacing(33);
        
        table.addCell(db.createEmptyCell(1, 2));

        table.addCell(db.createEmptyCell(1, 2));

        table.addCell(db.createCell(1, 1)
        		.setBorderTop(new SolidBorder(0.7f))
        		.add(db.createParagraph("Cliente (contratante)").setTextAlignment(TextAlignment.CENTER).setFontSize(fontSize)));
        table.addCell(db.createCell(1, 1)
        		.setBorderTop(new SolidBorder(0.7f))
        		.add(db.createParagraph("Respons�vel  pelo Departamento Comercial").setTextAlignment(TextAlignment.CENTER).setFontSize(fontSize)));

        table.addCell(db.createEmptyCell(1, 2));

        table.addCell(db.createEmptyCell(1, 2));
        
        table.addCell(db.createCell(1, 1)
        		.setBorderTop(new SolidBorder(0.7f))
        		.add(db.createParagraph("Dire��o Adm./Financeira (contratada)").setTextAlignment(TextAlignment.CENTER).setFontSize(fontSize)));
        table.addCell(db.createCell(1, 1)
        		.setBorderTop(new SolidBorder(0.7f))
        		.add(db.createParagraph("Corretor").setTextAlignment(TextAlignment.CENTER).setFontSize(fontSize)));
        
        return table;
        
	}

}
