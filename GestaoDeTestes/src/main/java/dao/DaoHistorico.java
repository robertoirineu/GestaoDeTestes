package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dominio.Historico;
import dominio.Projeto;
import dominio.Usuario;
import enumeradores.Status;
import enumeradores.StatusHistorioco;
import factory.HibernateManageFactory;

public class DaoHistorico {

	public static boolean salvaHistorico(Historico historico) {
		if (historico.getId() != null) {
			return DaoHistorico.alterar(historico);

		} else {
			return DaoHistorico.persist(historico);
		}
	}

	private static boolean persist(Historico historico) {

		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(historico);
			entityManager.getTransaction().commit();

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

		return false;

	}

	private static boolean alterar(Historico historico) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		entityManager.find(Historico.class, historico.getId());

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(historico);
			entityManager.getTransaction().commit();

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();
		}
		return false;
	}

	public static Historico buscarHistorico(Long historicoId) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			Query query = entityManager.createQuery("SELECT h FROM Historico h WHERE h.id = :historicoId",
					Historico.class);
			query.setParameter("historicoId", historicoId);
			Historico historico = (Historico) query.getSingleResult();

			return historico;

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Historico> listaHistoricoPorProjeto(Projeto projeto) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		try {
			Query q = entityManager.createQuery("select h from Historico h where h.projeto = :projeto",
					Historico.class);
			q.setParameter("projeto", projeto);
			return q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public static List<Historico> historicos(boolean historicoAtrasado,boolean historicoNoprazo, boolean projetoInativo, boolean historicoFechado, Usuario usuario,
			Date dataInicial, Date dataFinal) {
		Session session = HibernateManageFactory.getEntityManager().unwrap(Session.class);

		try {
			Criteria cri = session.createCriteria(Historico.class, "historico");
			cri.createAlias("historico.projeto", "projeto");

			if (historicoAtrasado)
				cri.add(Restrictions.lt("historico.dataRealizacao", new Date()));
			
			if (historicoNoprazo)
				cri.add(Restrictions.gt("historico.dataRealizacao", new Date()));
			
			if (!historicoFechado)
				cri.add(Restrictions.eq("historico.statusHistorico", StatusHistorioco.ABERTO));

			if (!projetoInativo)
				cri.add(Restrictions.eq("projeto.statusProjeto", Status.ATIVO));

			
			if (dataInicial != null && dataFinal != null)
				cri.add(Restrictions.between("historico.dataRealizacao", dataInicial, dataFinal));
			else if (dataFinal != null)
				cri.add(Restrictions.lt("historico.dataRealizacao", dataFinal));

			if (usuario != null)
				cri.add(Restrictions.eq("historico.responsavel", usuario));
			
			return (List<Historico>) cri.list();

		} finally {
			session.close();
		}
	}

	public static boolean excluirHistorico(Historico historico) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {

			Historico historicoEx = entityManager.getReference(Historico.class, historico.getId());
			entityManager.getTransaction().begin();
			entityManager.remove(historicoEx);
			entityManager.getTransaction().commit();

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();

		}
		return false;
	}
}
