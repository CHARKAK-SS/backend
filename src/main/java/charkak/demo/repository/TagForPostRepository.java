package charkak.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import charkak.demo.model.Post;
import charkak.demo.model.TagForPost;

public interface TagForPostRepository extends JpaRepository<TagForPost, Long> {
    TagForPost findByPost(Post post);
}
