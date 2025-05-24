package charkak.demo.dto;

import charkak.demo.model.Post;
import charkak.demo.model.TagForPost;
import lombok.Getter;

@Getter
public class PostResponseDto {
    private Long id;
    private String placeName;
    private String dateTime;
    private String imageUrl;
    private String ratingTagName;
    private String countryTagName;
    private String cityTagName;
    private String targetTagName;

    public PostResponseDto(Post post, TagForPost tagForPost) {
        this.id = post.getId();
        this.placeName = post.getPlaceName();
        this.dateTime = post.getDateTime().toString();
        this.imageUrl = post.getImageUrl();
        this.ratingTagName = tagForPost.getRatingTag() != null ? tagForPost.getRatingTag().getName() : null;
        this.countryTagName = tagForPost.getCountryTag() != null ? tagForPost.getCountryTag().getName() : null;
        this.cityTagName = tagForPost.getCityTag() != null ? tagForPost.getCityTag().getName() : null;
        this.targetTagName = tagForPost.getTargetTag() != null ? tagForPost.getTargetTag().getName() : null;
    }
}
