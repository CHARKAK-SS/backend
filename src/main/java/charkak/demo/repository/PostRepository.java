package charkak.demo.repository;

import charkak.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import charkak.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPlaceName(String placeName);  // 🔥 장소명으로 검색 추가
}
