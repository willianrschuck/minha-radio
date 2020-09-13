package br.com.willianschuck.document;

import java.io.ByteArrayOutputStream;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class DocumentBuilder {

	private static float margin = 36f; 
	
	private ByteArrayOutputStream os;
	private Document document;
	
	@SuppressWarnings("unused")
	private PdfFont baseFont;
	
	private PdfFont baseFontBold;

	private Style baseStyle;
	
	public DocumentBuilder() {
		
		 os = new ByteArrayOutputStream();
		 document = new Document(new PdfDocument(new PdfWriter(os)), PageSize.A4);
		 document.setMargins(margin, margin, margin, margin);
		 
	}
	
	public byte[] toByteArray() {
		document.close();
		return os.toByteArray();
	}
	

	public void addToDocument(IBlockElement element) {
		document.add(element);
	}

	public void setBaseFont(PdfFont baseFont) {
		this.baseFont = baseFont;
	}

	public void setBaseFontBold(PdfFont baseFontBold) {
		this.baseFontBold = baseFontBold;
	}

	public void setBaseStyle(Style baseStyle) {
		this.baseStyle = baseStyle;
	}
	

	public Table createTable(int numberOfColumns) {
		return new Table(UnitValue.createPercentArray(numberOfColumns)).setBorder(Border.NO_BORDER).useAllAvailableWidth();
	}	
	
	public ListItem createListItem(String text) {
		return createListItem(text, baseStyle);
	}

	public ListItem createListItem(String text, Style style) {
		ListItem listItem = new ListItem();
		listItem.add(createParagraph(text, style));
		return listItem;
	}
	
	public Cell createCell(int rowspan, int colspan) {
		return new Cell(rowspan, colspan).setBorder(Border.NO_BORDER).setPadding(0);
	}

	public Cell createEmptyCell(int rowspan, int colspan) {
		return createCell(rowspan, colspan).add(createParagraph(".", new Style()).setOpacity(0f));
	}
	
	public Paragraph createParagraph(String text) {
		return new Paragraph(text).addStyle(baseStyle).setMultipliedLeading(1.2f);
	}
	
	public Paragraph createParagraph(String text, Style style) {
		return createParagraph(text).addStyle(style);
	}

	public Cell createParagraphCell(int colspan, String text, Style style) {
		return createCell(1, colspan).add(createParagraph(text, style));
	}

	public Cell createParagraphCell(int colspan, String text) {
		return createParagraphCell(colspan, text, baseStyle);
	}

	public Cell createLabelCell(int colspan, String label, String textValue) {
		return createCell(1, colspan).add(createParagraph(label).setFont(baseFontBold)
												.add(createParagraph(textValue)));
	}
	
}
