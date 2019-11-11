package util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class Main {
	public static void main(String[] args) throws IOException {
		/*List<Projeto> projetos = DaoProjeto.listarProjetos();

		if (projetos.size() > 0) {
			System.out.println(projetos.size());
			Relatorio.projetos(projetos);
		}

		// exibe no terminal o local onde o arquivo se encontra
		System.out.print(new File("Relatório de projetos.pdf").getAbsolutePath());
		System.out.print("\n");
		System.out.print(new File(".").getCanonicalPath());
		Usuario u = DaoUsuario.buscaUsuario("roberto@gmail.com");
		List<Projeto> p = DaoProjeto.projetos(true, u, null, new Date());

		System.out.print(p.size());*/
		Calendar calendarOntem = Calendar.getInstance();
		calendarOntem.setTime(new Date());
		calendarOntem.set(Calendar.DAY_OF_MONTH, calendarOntem.get(Calendar.DAY_OF_MONTH) - 1);
		Date ontem = calendarOntem.getTime();
		System.out.println(ontem.toString());
		System.out.println(new Encode().encript("123456789"));
		System.out.println(new Encode().decrip("wrPCtMK1wrbCt8K4wrnCusK7"));
		System.out.println(new File(".").getCanonicalPath()+"/src/main/webapp/resources/images/usuNull.png");

	}
}
