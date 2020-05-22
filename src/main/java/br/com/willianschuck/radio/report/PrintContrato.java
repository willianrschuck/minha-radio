package br.com.willianschuck.radio.report;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderCollapsePropertyValue;
import com.itextpdf.layout.property.ListNumberingType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import br.com.willianschuck.document.DocumentBuilder;
import br.com.willianschuck.radio.model.Contrato;
import br.com.willianschuck.radio.model.Endereco;

public class PrintContrato {
	
	private SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
	
	private static final PdfFont baseFont = buildBaseFont();
	private static final PdfFont baseFontBold = buildBaseFontBold();
	
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
	
	private static String initTerms = "A %s,  situada na %s, %s � %s, nesta cidade de %s Estado de %s, inscrita no CNPJ sob n� 00.111.222/0001-00, doravante denominada CONTRATADA, compromete-se a veicular an�ncios institucionais, na forma de APOIO CULTURAL, nas seguintes condi��es:";
	private static String clausulaPrimeira = "O presente contrato somente entrar� em vigor ap�s a sua aprova��o pela Dire��o Geral da CONTRATADA.";
	private static String clausulaSegunda = "O cliente doravante denominado de CONTRATANTE, autoriza a emiss�o das faturas e respectivas duplicatas, que poder�o ser negociadas atrav�s de bancos ou cobradores credenciados pela CONTRTADA.";
	private static String clausulaTerceira = "Todas as altera��es a serem introduzidas neste contrato, bem como todos os cancelamentos, por parte do contratante, dever�o ser obrigatoriamente efetuados por escrito, com anteced�ncia de 72 (setenta e duas) horas.";
	private static String clausulaQuarta = "O valor a ser faturado e desde j� autorizado, corresponder� �s somas da inser��es transmitidas pela emissora at� o limite das verbas consignadas neste contrato, correspondendo, d�vida l�quida e certa pass�vel de execu��o judicial ou extra judicial, independente de aceite das duplicatas emitidas em fun��o deste contrato.";
	private static String clausulaQuinta = "O atraso nos pagamentos das duplicatas emitidas em decorr�ncia do presente contrato, acarretar� a suspens�o autom�tica dos primeiros an�ncios, podendo a CONTRATADA, por esse motivo, considerar rescindido o presente contrato.";
	private static String clausulaSexta = "O CONTRATANTE poder� fazer modifica��es na mensagem que estiver sendo veiculada, desde que avise � CONTRATADA com anteced�ncia de 72 (setenta e duas) horas, que ter� aprova��o da Diretoria.";
	private static String clausulaSetima = "O CONTRATANTE, poder� usufluir os benef�cios da Lei 8.313 de 23/12/91, Lei de Incentivo � Cultura.";
	private static String clausulaOitava = "A CONTRATADA ceder� � CONTRATANTE, � t�tulo de cortesia, seus t�cnicos, studios e equipamento para grava��o da mensagem a ser veiculada. Com exce��o ao trabalho de locu��o, que caso exista alguma prefer�ncia do contratante.";
	private static String clausulaNona = "presente contrato poder� ser rescindido por qualquer uma das partes, a qualquer tempo, sem que haja penalidades, obsevando-se apenas a comunica��o com 72 (setenta e duas) horas de anteced�ncia.";
	private static String clausulaDecima = "As partes elegem o foro de [Cidade], para dirimir as d�vidas oriundas do presente contrato, excluindo qualquer outro por mais privilegiado que seja.";

	private Contrato contrato;

	public byte[] generate(Contrato contrato) {
		
		this.contrato = contrato;
		
		DocumentBuilder db = new DocumentBuilder();
		db.setBaseFont(baseFont);
		db.setBaseFontBold(baseFontBold);
		db.setBaseStyle(new Style().setMargin(0).setPadding(0).setFontSize(11).setBorder(Border.NO_BORDER).setFont(baseFont).setTextAlignment(TextAlignment.JUSTIFIED));
		
        Table docTable = db.createTable(1);
        
        Table infoTable = buildInfoTable(db);
        Table termsTable = buildTermsTable(db);
        Table authTable = buildAuthTable(db);
        
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(infoTable));
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(new LineSeparator(new SolidLine(0.25f))));
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(termsTable));
        docTable.addCell(new Cell().setBorder(Border.NO_BORDER).add(authTable));
		
		db.addToDocument(docTable);
		
		return db.toByteArray();
		
	}

	private Table buildTermsTable(DocumentBuilder db) {

		Style style = new Style().setFontSize(10);
		
		Table table = db.createTable(12);

		String nomeFantasia = contrato.getRadio().getNomeFantasia();
		Endereco endereco = contrato.getRadio().getEndereco();
		String terms = String.format(initTerms, nomeFantasia, endereco.getLogradouro(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade().getNome(), endereco.getCidade().getEstado().getNome());
		
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
        
		return table;
		
	}

	@SuppressWarnings("deprecation")
	private Table buildInfoTable(DocumentBuilder db) {
		
		Table infoTable = db.createTable(12);
        
        Style titleStyle = new Style().setFont(baseFontBold).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
        Style subtitleStyle = new Style().setFont(baseFontBold).setFontSize(11).setTextAlignment(TextAlignment.CENTER);
		infoTable.addCell(db.createParagraphCell(12, "CONTRATO DE APOIO CULTURAL", titleStyle));
        infoTable.addCell(db.createParagraphCell(12, "Lei n� 8313 de 23/12/91", subtitleStyle));
        
        infoTable.addCell(db.createEmptyCell(1, 12));

        infoTable.addCell(db.createLabelCell(12, "Raz�o Social: ", "Mercado Ga�cho Coqueiros do Sul - JOICE E ASSOCIADOS"));
        
        infoTable.addCell(db.createLabelCell(6, "Nome Fantasia: ", "Mercado Ga�cho"));
        infoTable.addCell(db.createLabelCell(6, "Ramo de Atividade: ", "Com�rcio"));
        
        infoTable.addCell(db.createLabelCell(6, "Endere�o: ", "Coqueiros do Sul"));
        infoTable.addCell(db.createLabelCell(2, "N�: ", "12"));
        infoTable.addCell(db.createLabelCell(4, "Bairro: ", "Centro"));
        
        infoTable.addCell(db.createLabelCell(6, "CNPJ: ", "62.140.365/0001-79"));
        infoTable.addCell(db.createLabelCell(6, "Inscric�o Estadual: ", "368/9026174"));
        
        infoTable.addCell(db.createLabelCell(6, "Tipo de An�ncio: ", "Tipo Teste"));
        infoTable.addCell(db.createLabelCell(6, "Dura��o: ", "8 segundos"));

        infoTable.addCell(db.createLabelCell(6, "Prazo deste Contrato: ", "6 meses"));
        infoTable.addCell(db.createLabelCell(3, "In�cio: ", dateFormater.format(new Date())));
        infoTable.addCell(db.createLabelCell(3, "T�rmino: ", dateFormater.format(new Date(120, 11, 11))));

        infoTable.addCell(db.createLabelCell(6, "Pre�o Mensal: ", "R$ 75,00"));
        infoTable.addCell(db.createLabelCell(6, "Pre�o Total: ", "R$ 450,00"));
        
        Paragraph paragraph = db.createParagraph("Discrimina��o das Inser��es Programa: ").setFont(baseFontBold).setTextAlignment(TextAlignment.LEFT)
        	.add(db.createParagraph("A pr�tica cotidiana prova que a complexidade dos estudos efetuados nos obriga � an�lise das condi��es financeiras e administrativas exigidas."));
        
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
        		.add(db.createParagraph("Respons�vel  pelo Departamento comercial").setTextAlignment(TextAlignment.CENTER).setFontSize(fontSize)));

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
