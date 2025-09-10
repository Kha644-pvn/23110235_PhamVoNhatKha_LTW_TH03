package vn.iostar.services;

import java.util.List;

import vn.iostar.models.Category;


public interface CategoryService {
	 boolean addCategory(Category c);
	    boolean updateCategory(Category c, int userid, String roleName);
	    boolean deleteCategory(int id, int userid, String roleName);
	    Category getCategoryById(int id);
	    List<Category> getAllCategories();
	    List<Category> getCategoriesForHome(int roleid, int userid);
}
