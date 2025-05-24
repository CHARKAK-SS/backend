package charkak.demo.repository;

import charkak.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // 로그인 시 사용
    boolean existsByUsername(String username); // 중복 확인
}
