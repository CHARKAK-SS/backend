package charkak.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date; // 캘린더에 해당하는 날짜

    @Column(nullable = true)
    private String location; // 출사 장소

    @Column(nullable = true, length = 1000)
    private String diaryText; // 일기 내용

    @Column(nullable = true)
    private String imageUrl; // EC2에 업로드된 이미지 URL

    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성 일시
}
