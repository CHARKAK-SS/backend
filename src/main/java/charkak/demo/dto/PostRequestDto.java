package charkak.demo.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String placeName;
    private String dateTime;
    private String camera;
    private String lens;
    private String aperture;
    private String shutterSpeed;
    private String iso;
    private String weather;
    private String imageUrl;
    private String thumbnailUrl;
    private String text;
    private Long userId;

    private String ratingTagName;
    private String countryTagName;
    private String cityTagName;
    private String targetTagName;
}
