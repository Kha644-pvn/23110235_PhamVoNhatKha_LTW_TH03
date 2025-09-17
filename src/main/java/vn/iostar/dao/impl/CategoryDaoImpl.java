package vn.iostar.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iostar.dao.CategoryDao;
import vn.iostar.models.Category;
import vn.iostar.utils.JPAUtil;

public class CategoryDaoImpl implements CategoryDao {
	@Override
    public boolean add(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Lỗi thêm Category: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // Update category của user thường (chỉ được update nếu sở hữu)
    @Override
    public boolean update(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Category existing = em.find(Category.class, c.getId());
            if (existing != null && existing.getUser().getId() == c.getUser().getId()) {
                existing.setName(c.getName());
                existing.setImage(c.getImage());
                em.merge(existing);
            } else {
                return false; // không phải user sở hữu category
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Lỗi cập nhật Category: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // Delete category của user thường
    @Override
    public boolean delete(int id, int userid) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Category c = em.find(Category.class, id);
            if (c != null && c.getUser().getId() == userid) {
                em.remove(c);
            } else {
                return false;
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Lỗi xóa Category: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public Category findById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Category> q = em.createQuery("SELECT c FROM Category c", Category.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Category> getByUserId(int userid) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Category> q = em.createQuery(
                    "SELECT c FROM Category c WHERE c.user.id = :uid", Category.class);
            q.setParameter("uid", userid);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    // Update category bởi manager/admin
    @Override
    public boolean updateByManager(Category c) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Category existing = em.find(Category.class, c.getId());
            if (existing != null) {
                existing.setName(c.getName());
                existing.setImage(c.getImage());
                em.merge(existing);
            } else {
                return false;
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Lỗi update Category (manager): " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    // Delete category bởi manager/admin
    @Override
    public boolean deleteByManager(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Category c = em.find(Category.class, id);
            if (c != null) em.remove(c);
            else return false;
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Lỗi delete Category (manager): " + e.getMessage(), e);
        } finally {
            em.close();
        }

	}
    
    @Override
    public List<Category> searchByName(String keyword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Category> q = em.createQuery(
                "SELECT c FROM Category c WHERE c.name LIKE :kw", Category.class);
            q.setParameter("kw", "%" + keyword + "%");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

	

}
