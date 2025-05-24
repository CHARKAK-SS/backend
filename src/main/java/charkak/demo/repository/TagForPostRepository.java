package charkak.demo.repository;

import charkak.demo.model.TagForPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagForPostRepository extends JpaRepository<TagForPost, Long> {
    // TagForPost 엔티티에 대한 CRUD를 JpaRepository가 제공
}
