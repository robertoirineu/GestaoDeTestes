package relatorios;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.JFreeChart;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import dominio.Projeto;
import dominio.Usuario;
import util.Mensagem;

public class Relatorio {
	private static Document documento;

	public static boolean usuario(Usuario usuario) {

		documento = new Document();

		try {

			String path = new File(".").getCanonicalPath();
			PdfWriter.getInstance(documento, new FileOutputStream(path + "/" + usuario.getNomeUsuario() + ".pdf"));
			documento.open();

			// adicionando um parágrafo no documento
			documento.add(new Paragraph("Gerando PDF - Java"));
			documento.close();

			// PDF.downloadPDF(usuario.getNomeUsuario() + ".PDF", path);
			return true;

		} catch (DocumentException de) {
			Mensagem.falha(de.getMessage());
		} catch (IOException ioe) {
			Mensagem.falha(ioe.getMessage());
		}
		documento.close();
		return false;

	}

	public static boolean projetos(List<Projeto> projetos) {
		documento = new Document();
		try {
			String path = new File(".").getCanonicalPath();
			System.out.println(path);
			PdfWriter writer = PdfWriter.getInstance(documento,
					new FileOutputStream(path + "/RelatórioDeProjetos.pdf"));
			documento.open();

			// adicionando um parágrafo no documento
			documento.add(new Paragraph("RELATÓRIO DE PROJETOS"));

			// add image
			int width = 495;
			int height = 300;
			JFreeChart chart = Chart.pieProjetos(projetos);
			BufferedImage bufferedImage = chart.createBufferedImage(width, height);
			Image image = Image.getInstance(writer, bufferedImage, 1.0f);

			documento.add(image);
			documento.add(Tabela.projetos(projetos));

			return true;

		} catch (DocumentException de) {
			Mensagem.falha(de.getMessage());

		} catch (IOException ioe) {
			Mensagem.falha(ioe.getMessage());

		} finally {
			documento.close();

		}

		return false;

	}

}
