package vn.iostar.utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	 private static final String PERSISTENCE_UNIT_NAME = "TH02";
	    private static EntityManagerFactory emf;

	    private static synchronized EntityManagerFactory getEntityManagerFactory() {
	        if (emf == null) {
	            try {
	                emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	            } catch (Exception e) {
	                throw new RuntimeException("Lỗi tạo EntityManagerFactory: " + e.getMessage(), e);
	            }
	        }
	        return emf;
	    }

	    public static EntityManager getEntityManager() {
	        return getEntityManagerFactory().createEntityManager();
	    }

	    public static void close() {
	        if (emf != null && emf.isOpen()) {
	            emf.close();
	        }
	    }

}
