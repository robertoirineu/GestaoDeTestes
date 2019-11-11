package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dominio.Cliente;
import enumeradores.Status;
import factory.HibernateManageFactory;

public class DaoCliente {


	public static void salvar(Cliente cliente) {
		if (cliente.getCodCliente() != null)
			DaoCliente.alterar(cliente);
		else
			DaoCliente.persist(cliente);
	}

	public static boolean persist(Cliente cliente) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		if (cliente.getNomeCliente() != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(cliente);
				entityManager.getTransaction().commit();

				return true;

			} catch (Exception ex) {
				ex.printStackTrace();
				entityManager.getTransaction().rollback();
			} finally {
				entityManager.close();
			}
		}

		return false;

	}

	public static boolean alterar(Cliente cliente) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		entityManager.find(Cliente.class, cliente.getCodCliente());
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(cliente);
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

	public static void excluir(String nomeCliente) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			String busca = "Select a from Cliente a where a.nomecliente = :nomecliente";
			Query query = entityManager.createQuery(busca);

			query.setParameter("nomecliente", nomeCliente);
			Cliente cliente = (Cliente) query.getSingleResult();

			entityManager.getTransaction().begin();
			entityManager.remove(cliente);
			entityManager.getTransaction().commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public static Cliente buscaClientePeloNome(String nomeCliente) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		Cliente cliente = new Cliente();

		try {
			Query query = entityManager.createQuery("Select cl from Cliente cl where cl.nomeCliente = :nomeCliente",
					Cliente.class);
			query.setParameter("nomeCliente", nomeCliente);

			cliente = (Cliente) query.getSingleResult();
			return cliente;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Cliente> listarClientes() {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		ArrayList<Cliente> clientes;

		try {

			Query q = entityManager.createQuery("select cli from Cliente cli", Cliente.class);
			clientes = (ArrayList<Cliente>) q.getResultList();

			return clientes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Cliente> listarClientesAtivos() {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			Query q = entityManager.createQuery("select cli from Cliente cli where cli.statusCliente = :status",
					Cliente.class);
			q.setParameter("status", Status.ATIVO);
			return q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}

	public static void detachCliente(Cliente cli) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			entityManager.detach(cli);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			entityManager.close();
		}
	}
}
