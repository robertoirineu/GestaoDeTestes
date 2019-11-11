package factory;

import java.io.Closeable;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateManageFactory implements Closeable{
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("default");

	public static EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}

	@Override
	public void close() throws IOException {
		EMF.close();		
	}

}
