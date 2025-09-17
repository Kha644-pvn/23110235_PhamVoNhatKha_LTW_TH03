package vn.iostar.models;
import jakarta.persistence.*;

@Entity
@Table(name = "Video")
public class Video {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(length = 500)
    private String description;

    @Lob
    @Column(columnDefinition = "VARBINARY(MAX)", nullable = true)
    private byte[] thumbnail;  // ảnh thumbnail

    @Column(nullable = false, length = 200)
    private String url;        // link video

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user; // người upload

    @Transient
    private String thumbnailBase64;

    public Video() {}

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public byte[] getThumbnail() { return thumbnail; }
    public void setThumbnail(byte[] thumbnail) { this.thumbnail = thumbnail; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getThumbnailBase64() {
        if (thumbnail != null) {
            return java.util.Base64.getEncoder().encodeToString(thumbnail);
        }
        return null;
    }

    public void setThumbnailBase64(String thumbnailBase64) {
        this.thumbnailBase64 = thumbnailBase64;
    }

}
