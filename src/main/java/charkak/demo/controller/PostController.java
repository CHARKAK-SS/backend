package charkak.demo.controller;

import charkak.demo.dto.PostRequestDto;
import charkak.demo.model.Post;
import charkak.demo.model.Tag;
import charkak.demo.model.TagForPost;
import charkak.demo.model.User;
import charkak.demo.repository.PostRepository;
import charkak.demo.repository.TagRepository;
import charkak.demo.repository.TagForPostRepository;
import charkak.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final TagForPostRepository tagForPostRepository;

    // ✅ 게시글 등록
    @PostMapping
    public String createPost(@RequestBody PostRequestDto request) {
        Optional<User> userOpt = userRepository.findById(request.getUserId());
        if (userOpt.isEmpty()) {
            return "❌ 사용자 ID 없음";
        }

        Post post = Post.builder()
                .placeName(request.getPlaceName())
                .dateTime(Timestamp.valueOf(request.getDateTime()))
                .camera(request.getCamera())
                .lens(request.getLens())
                .aperture(request.getAperture())
                .shutterSpeed(request.getShutterSpeed())
                .iso(request.getIso())
                .weather(request.getWeather())
                .imageUrl(request.getImageUrl())
                .text(request.getText())
                .user(userOpt.get())
                .build();
        postRepository.save(post);

        // 태그 저장
        Tag ratingTag = tagRepository.findByName(request.getRatingTagName());
        Tag countryTag = tagRepository.findByName(request.getCountryTagName());
        Tag cityTag = tagRepository.findByName(request.getCityTagName());
        Tag targetTag = tagRepository.findByName(request.getTargetTagName());

        TagForPost tagForPost = TagForPost.builder()
                .post(post)
                .ratingTag(ratingTag)
                .countryTag(countryTag)
                .cityTag(cityTag)
                .targetTag(targetTag)
                .build();
        tagForPostRepository.save(tagForPost);

        return "✅ 포스트 저장 완료";
    }

    // ✅ 전체 게시글 조회
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글 없음");
        }
    }
}
