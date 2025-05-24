package charkak.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TagForPost {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "rating_tag_id")
    private Tag ratingTag;

    @ManyToOne
    @JoinColumn(name = "country_tag_id")
    private Tag countryTag;

    @ManyToOne
    @JoinColumn(name = "city_tag_id")
    private Tag cityTag;

    @ManyToOne
    @JoinColumn(name = "target_tag_id")
    private Tag targetTag;
}
