package charkak.demo.service;

import charkak.demo.model.Calendar;
import charkak.demo.model.User;
import charkak.demo.repository.CalendarRepository;
import charkak.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader; // ✅ S3Uploader 주입

    public Calendar saveCalendar(Calendar calendar, String username, MultipartFile imageFile) throws IOException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        // ✅ S3에 업로드
        if (imageFile != null && !imageFile.isEmpty()) {
            String imageUrl = s3Uploader.upload(imageFile, "calendar"); // "calendar"는 S3 디렉토리 이름
            calendar.setImageUrl(imageUrl);
        }

        calendar.setUser(user);
        return calendarRepository.save(calendar);
    }

    public List<Calendar> getUserCalendars(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        return calendarRepository.findByUserId(user.getId());
    }

    public Calendar saveCalendarWithImage(Calendar calendar, String username, MultipartFile imageFile) throws IOException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        // S3 업로드
        String imageUrl = s3Uploader.upload(imageFile, "calendar");
        calendar.setImageUrl(imageUrl);
        calendar.setUser(user);

        return calendarRepository.save(calendar);
    }

    public Calendar saveCalendarJson(String username, String location, String diaryText, String imageUrl, LocalDate date, LocalDateTime createdAt) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Calendar calendar = Calendar.builder()
                .user(user)
                .location(location)
                .diaryText(diaryText)
                .imageUrl(imageUrl)
                .date(date)
                .createdAt(createdAt)
                .build();

        return calendarRepository.save(calendar);
    }


}