package vn.iostar.services.impl;

import java.util.List;
import java.util.Base64;



import vn.iostar.services.CategoryService;
import vn.iostar.models.Category;
import vn.iostar.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;



public class CategoryServiceImpl implements CategoryService {
	
	 @Override
	    public boolean addCategory(Category c) {
	        EntityManager em = JPAUtil.getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            em.persist(c); // IDENTITY tự động tăng
	            tx.commit();
	            return true;
	        } catch (Exception e) {
	            if (tx.isActive()) tx.rollback();
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
	    }
	    
	    @Override
	    public boolean updateCategory(Category c, int userid, String roleName) {
	        EntityManager em = JPAUtil.getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            Category existing = em.find(Category.class, c.getId());
	            if (existing == null) return false;

	            if ("manager".equals(roleName) || existing.getUser().getId() == userid) {
	                existing.setName(c.getName());
	                if (c.getImage() != null) existing.setImage(c.getImage());
	                em.merge(existing);
	                tx.commit();
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            if (tx.isActive()) tx.rollback();
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public boolean deleteCategory(int id, int userid, String roleName) {
	        EntityManager em = JPAUtil.getEntityManager();
	        EntityTransaction tx = em.getTransaction();
	        try {
	            tx.begin();
	            Category c = em.find(Category.class, id);
	            if (c == null) return false;

	            if ("manager".equals(roleName) || c.getUser().getId() == userid) {
	                em.remove(c);
	                tx.commit();
	                return true;
	            }
	            return false;
	        } catch (Exception e) {
	            if (tx.isActive()) tx.rollback();
	            e.printStackTrace();
	            return false;
	        } finally {
	            em.close();
	        }
	    }


	    @Override
	    public Category getCategoryById(int id) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            Category c = em.find(Category.class, id);
	            if (c != null && c.getImage() != null) {
	                c.setImageBase64(Base64.getEncoder().encodeToString(c.getImage()));
	            }
	            return c;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Category> getCategoriesForHome(int roleid, int userid) {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            TypedQuery<Category> query;
	            if (roleid == 1) { // Manager
	                query = em.createQuery("SELECT c FROM Category c", Category.class);
	            } else { // Admin/User
	                query = em.createQuery("SELECT c FROM Category c WHERE c.user.id = :uid", Category.class);
	                query.setParameter("uid", userid);
	            }
	            List<Category> list = query.getResultList();

	            // Encode imageBase64
	            for (Category c : list) {
	                if (c.getImage() != null) {
	                    c.setImageBase64(Base64.getEncoder().encodeToString(c.getImage()));
	                }
	            }
	            return list;
	        } finally {
	            em.close();
	        }
	    }

	    @Override
	    public List<Category> getAllCategories() {
	        EntityManager em = JPAUtil.getEntityManager();
	        try {
	            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
	            List<Category> list = query.getResultList();
	            for (Category c : list) {
	                if (c.getImage() != null) {
	                    c.setImageBase64(Base64.getEncoder().encodeToString(c.getImage()));
	                }
	            }
	            return list;
	        } finally {
	            em.close();
	        }
	    }

}
