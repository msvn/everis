import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.derby.jdbc.EmbeddedDataSource;
import org.apache.naming.java.javaURLContextFactory;

public class Main {

	public static void main(String[] args) throws Exception {
		initDB();

		EntityManager entityManager = Persistence.createEntityManagerFactory(
				"PU").createEntityManager();

		entityManager.getTransaction().begin();

		User user = new User();

		user.setName(Long.toString(new Date().getTime()));

		entityManager.persist(user);

		entityManager.getTransaction().commit();

		// see that the ID of the user was set by Hibernate
		System.out.println("user=" + user + ", user.id=" + user.getId());

//		User foundUser = entityManager.find(User.class, user.getId());
		
		Query q = entityManager.createQuery("from User");

		// note that foundUser is the same instance as user and is a concrete
		// class (not a proxy)
//		System.out.println("foundUser=" + foundUser);

//		System.out.println(user.getName() + foundUser.getName());

		entityManager.close();

		stopDB();
	}

	private static void initDB() throws Exception {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
				javaURLContextFactory.class.getName());
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		InitialContext ic = new InitialContext();

		ic.createSubcontext("java:");
		ic.createSubcontext("java:comp");
		ic.createSubcontext("java:comp/env");
		ic.createSubcontext("java:comp/env/jdbc");

		EmbeddedDataSource ds = new EmbeddedDataSource();
		ds.setDatabaseName("DB");
		// tell Derby to create the database if it does not already exist
		ds.setCreateDatabase("create");

		ic.bind("java:comp/env/jdbc/DS", ds);
	}

	private static void stopDB() throws Exception {
		InitialContext ic = new InitialContext();
		ic.unbind("java:comp/env/jdbc/DS");
	}
}
