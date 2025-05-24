package charkak.demo.repository;

import charkak.demo.model.TagForPost;
import org.springframework.data.jpa.repository.JpaRepository;

import charkak.demo.model.Post;

public interface TagForPostRepository extends JpaRepository<TagForPost, Long> {
    TagForPost findByPost(Post post);
}
