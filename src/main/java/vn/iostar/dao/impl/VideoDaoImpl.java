package vn.iostar.dao.impl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.iostar.dao.VideoDao;
import vn.iostar.models.Video;
import vn.iostar.utils.JPAUtil;
import vn.iostar.models.User;


import java.util.List;

public class VideoDaoImpl implements VideoDao {
	 @Override
	 public boolean add(Video v) {
		    EntityManager em = JPAUtil.getEntityManager();
		    try {
		        em.getTransaction().begin();

		        // Lấy user từ DB trước khi gắn
		        if (v.getUser() != null && v.getUser().getId() > 0) {
		            User user = em.getReference(User.class, v.getUser().getId());
		            v.setUser(user);
		        }

		        em.persist(v);
		        em.getTransaction().commit();
		        return true;
		    } catch (Exception e) {
		        if (em.getTransaction().isActive()) em.getTransaction().rollback();
		        e.printStackTrace();
		        return false;
		    } finally {
		        em.close();
		    }
		}


	 @Override
	 public boolean update(Video v) {
	     EntityManager em = JPAUtil.getEntityManager();
	     try {
	         em.getTransaction().begin();
	         Video existing = em.find(Video.class, v.getId());
	         if (existing != null) {
	             existing.setTitle(v.getTitle());
	             existing.setDescription(v.getDescription());
	             existing.setUrl(v.getUrl());
	             existing.setThumbnail(v.getThumbnail());

	             // 🔹 Cập nhật lại user nếu có thay đổi
	             if (v.getUser() != null && v.getUser().getId() > 0) {
	                 User user = em.getReference(User.class, v.getUser().getId());
	                 existing.setUser(user);
	             }

	             em.merge(existing);
	             em.getTransaction().commit();
	             return true;
	         }
	         return false;
	     } catch (Exception e) {
	         if (em.getTransaction().isActive()) em.getTransaction().rollback();
	         e.printStackTrace();
	         return false;
	     } finally {
	         em.close();
	     }
	 }


	    @Override
	    public boolean delete(int id) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            em.getTransaction().begin();
	            Video v = em.find(Video.class, id);
	            if (v == null) return false;
	            em.remove(v);
	            em.getTransaction().commit();
	            return true;
	        } catch (Exception e) {
	            if (em.getTransaction().isActive()) em.getTransaction().rollback();
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public Video findById(int id) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            return em.find(Video.class, id);
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Video> getAll() {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Video> getByUserId(int userid) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            TypedQuery<Video> query = em.createQuery(
	                "SELECT v FROM Video v WHERE v.user.id = :uid", Video.class);
	            query.setParameter("uid", userid);
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Video> searchByTitle(String keyword) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            TypedQuery<Video> query = em.createQuery(
	                "SELECT v FROM Video v WHERE LOWER(v.title) LIKE LOWER(:kw)", Video.class);
	            query.setParameter("kw", "%" + keyword + "%");
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Video> searchByDescription(String keyword) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            TypedQuery<Video> query = em.createQuery(
	                "SELECT v FROM Video v WHERE LOWER(v.description) LIKE LOWER(:kw)", Video.class);
	            query.setParameter("kw", "%" + keyword + "%");
	            return query.getResultList();
	        } finally {
	            em.close();
	        }
	    }
	

}
