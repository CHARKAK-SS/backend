package charkak.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 사용자 아이디 (중복 X)

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private String password; // 비밀번호 (암호화됨)

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
