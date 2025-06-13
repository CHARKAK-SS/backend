package charkak.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placeName;
    private java.sql.Timestamp dateTime;
    private String camera;
    private String lens;
    private String aperture;
    private String shutterSpeed;
    private String iso;
    private String weather;
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
