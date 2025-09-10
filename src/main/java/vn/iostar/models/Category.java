package vn.iostar.models;
import jakarta.persistence.*;
@Entity
@Table(name = "Category")

public class Category {
	 @Id
	    private int id;

	    @Column(nullable = false, length = 200)
	    private String name;

	    @Lob
	    private byte[] image;

	    @ManyToOne
	    @JoinColumn(name = "userid", nullable = false)
	    private User user;

	    @Transient
	    private String imageBase64;

	    public Category() {}

	    // Getter & Setter
	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public byte[] getImage() { return image; }
	    public void setImage(byte[] image) { this.image = image; }

	    public User getUser() { return user; }
	    public void setUser(User user) { this.user = user; }

	    public String getImageBase64() {
	        if (image != null) {
	            return java.util.Base64.getEncoder().encodeToString(image);
	        }
	        return null;
	    }
	    public void setImageBase64(String imageBase64) { this.imageBase64 = imageBase64; }
	    public static int findNextAvailableId(EntityManager em) {
	        // Query để lấy danh sách id đã dùng, sắp xếp ASC
	        @SuppressWarnings("unchecked")
	        java.util.List<Integer> ids = em.createNativeQuery("SELECT id FROM Category ORDER BY id ASC")
	                                       .getResultList();
	        int nextId = 1;
	        for (Integer id : ids) {
	            if (id != nextId) break; // nếu id khác với nextId, nextId là số trống
	            nextId++;
	        }
	        return nextId;
	    }

}
