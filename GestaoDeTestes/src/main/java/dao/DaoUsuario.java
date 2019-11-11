package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.HibernateException;

import dominio.Usuario;
import enumeradores.Status;
import factory.HibernateManageFactory;

public class DaoUsuario {
	
	public static void salvar(Usuario usuario) throws HibernateException, Exception {
		if (usuario.getCodUsuario() != null)
			DaoUsuario.alterar(usuario);
		else
			DaoUsuario.persist(usuario);
	}

	private static boolean persist(Usuario usuario) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		if (usuario.getEmail() != null) {
			try {

				entityManager.getTransaction().begin();
				entityManager.persist(usuario);
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

	private static boolean alterar(Usuario usuario) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		entityManager.find(Usuario.class, usuario.getCodUsuario());
		
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(usuario);
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

	public static void excluir(String email) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {

			String busca = "Select a from Usuario a where a.email = :email";
			Query query = entityManager.createQuery(busca);

			query.setParameter("email", email);
			Usuario usuario = (Usuario) query.getSingleResult();

			entityManager.getTransaction().begin();
			entityManager.remove(usuario);
			entityManager.getTransaction().commit();
			

		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public static Usuario buscaUsuario(String email) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		Usuario usuario = new Usuario();

		try {
			Query query = entityManager.createQuery("Select a from Usuario a where a.email = :email", Usuario.class);
			query.setParameter("email", email);

			usuario = (Usuario) query.getSingleResult();
			return usuario;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}

	
	public static Usuario buscaUsuarioPeloNome(String nomeUsuario) {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		Usuario usuario = new Usuario();

		try {
			Query query = entityManager.createQuery("Select usu from Usuario usu where usu.nomeUsuario = :nome",
					Usuario.class);
			query.setParameter("nome", nomeUsuario);

			usuario = (Usuario) query.getSingleResult();
			return usuario;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Usuario> listarUsuarios() {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();
		ArrayList<Usuario> usuarios;

		try {

			Query q = entityManager.createQuery("select usu from Usuario usu", Usuario.class);
			usuarios = (ArrayList<Usuario>) q.getResultList();			

			return usuarios;
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			entityManager.close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Usuario> listarUsuariosAtivos() {
		EntityManager entityManager = HibernateManageFactory.getEntityManager();

		try {
			Query q = entityManager.createQuery("select usu from Usuario usu where usu.statusUsuario = :status",
					Usuario.class);
			q.setParameter("status", Status.ATIVO);
			return q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return null;
	}

	
}
