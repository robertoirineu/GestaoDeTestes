package relatorios;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import dominio.Projeto;

public class Chart {
	
	/**
	 * Gets a {@link JFreeChart}.
	 * 
	 * @return {@link JFreeChart}.
	 */
	public static JFreeChart pieProjetos(List<Projeto> projetos) {

		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Projeto p : projetos) {
			int qtdProjetos = 0;

			for (Projeto p2 : projetos) {
				if (p.getCliente().getCodCliente().equals(p2.getCliente().getCodCliente()))
					qtdProjetos++;
			}
			dataset.setValue(p.getCliente().getNomeCliente(), qtdProjetos);
		}
		
		JFreeChart chart = ChartFactory.createPieChart3D("Projetos por clientes", dataset, true, true, false);
		final PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
		
		PiePlot3D plot = ( PiePlot3D )chart.getPlot();
		plot.setLabelGenerator(labelGenerator);
		
		return chart;
	}
}
