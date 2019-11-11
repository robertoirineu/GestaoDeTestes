package relatorios;

import java.text.SimpleDateFormat;
import java.util.List;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import dominio.Projeto;

public class Tabela {
	public static PdfPTable projetos(List<Projeto> projetos) {
		Paragraph tableHead = new Paragraph("Projetos");
		PdfPTable table = new PdfPTable(6);
		PdfPCell header = new PdfPCell(tableHead);

		header.setColspan(6);
		
		table.addCell(header);
		table.addCell("Nome Cliente");
		table.addCell("Nome Projeto");
		table.addCell("Nome Analista");
		table.addCell("Nome Gerente");
		table.addCell("Status Projeto");
		table.addCell("Data Conclusão");

		for (Projeto p : projetos) {
			table.addCell(p.getCliente().getNomeCliente());
			table.addCell(p.getNomeProjeto());
			table.addCell(p.getAnalista().getNomeUsuario());
			table.addCell(p.getGerente().getNomeUsuario());
			table.addCell(p.getStatusProjeto().getStatus());
			table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(p.getDataProjetoFim()));
		}
		return table;
	}
}
