package charkak.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // 장소명

    @Column(nullable = false)
    private String address;  // 주소

    private String imageUrl; // 대표 이미지 URL (선택사항)
}
