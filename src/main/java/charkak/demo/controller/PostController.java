package charkak.demo.controller;

import charkak.demo.dto.PostRequestDto;
import charkak.demo.dto.PostResponseDto;
import charkak.demo.model.Post;
import charkak.demo.model.Tag;
import charkak.demo.model.TagForPost;
import charkak.demo.model.User;
import charkak.demo.repository.PostRepository;
import charkak.demo.repository.TagRepository;
import charkak.demo.repository.TagForPostRepository;
import charkak.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
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
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글 없음");
        }

        Post post = postOpt.get();
        TagForPost tagForPost = tagForPostRepository.findByPost(post);

        Map<String, Object> response = new HashMap<>();
        response.put("id", post.getId());
        response.put("placeName", post.getPlaceName());
        response.put("dateTime", post.getDateTime().toString());
        response.put("camera", post.getCamera());
        response.put("lens", post.getLens());
        response.put("aperture", post.getAperture());
        response.put("shutterSpeed", post.getShutterSpeed());
        response.put("iso", post.getIso());
        response.put("weather", post.getWeather());
        response.put("imageUrl", post.getImageUrl());
        response.put("text", post.getText());

        if (tagForPost != null) {
            response.put("ratingTag", tagForPost.getRatingTag().getName());
            response.put("countryTag", tagForPost.getCountryTag().getName());
            response.put("cityTag", tagForPost.getCityTag().getName());
            response.put("targetTag", tagForPost.getTargetTag().getName());
        }

        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    public List<PostResponseDto> getPostsByPlaceAndTags(
            @RequestParam String placeName,
            @RequestParam(required = false) String ratingTagName,
            @RequestParam(required = false) String countryTagName,
            @RequestParam(required = false) String cityTagName,
            @RequestParam(required = false) String targetTagName) {

        List<Post> posts = postRepository.findByPlaceName(placeName);
        return posts.stream()
                .map(post -> {
                    TagForPost tagForPost = tagForPostRepository.findByPost(post);
                    return new PostResponseDto(post, tagForPost);
                })
                .filter(dto -> {
                    boolean matches = true;
                    if (ratingTagName != null) matches &= ratingTagName.equals(dto.getRatingTagName());
                    if (countryTagName != null) matches &= countryTagName.equals(dto.getCountryTagName());
                    if (cityTagName != null) matches &= cityTagName.equals(dto.getCityTagName());
                    if (targetTagName != null) matches &= targetTagName.equals(dto.getTargetTagName());
                    return matches;
                })
                .toList();
    }




}
