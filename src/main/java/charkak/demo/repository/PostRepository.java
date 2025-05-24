package charkak.demo.repository;

import charkak.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // Post 엔티티에 대한 CRUD를 JpaRepository가 제공
}
