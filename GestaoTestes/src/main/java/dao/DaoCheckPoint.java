package dao;

import javax.persistence.EntityManager;

import dominio.CheckPoint;
import factory.HibernateManageFactory;

public class DaoCheckPoint {
	
	public static boolean salvaCheckPoint(CheckPoint checkPoint) {
		if (checkPoint.getId() != null)
			return DaoCheckPoint.alterar(checkPoint);
		else
			return DaoCheckPoint.persist(checkPoint);

	}

	private static boolean persist(CheckPoint checkPoint) {

		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.persist(checkPoint);
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

	private static boolean alterar(CheckPoint checkPoint) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		entityManager.find(CheckPoint.class, checkPoint.getId());

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(checkPoint);
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
