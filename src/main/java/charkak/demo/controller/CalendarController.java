package charkak.demo.controller;

import charkak.demo.model.Calendar;
import charkak.demo.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/save")
    public ResponseEntity<?> saveCalendar(@RequestBody Map<String, Object> payload) {
        try {
            String username = (String) payload.get("username");
            String location = (String) payload.get("location");
            String diaryText = (String) payload.get("diaryText");
            String imageUrl = (String) payload.get("imageUrl");
            LocalDate date = LocalDate.parse(((String) payload.get("date")).substring(0, 10));
            LocalDateTime createdAt = LocalDateTime.parse((String) payload.get("createdAt"));

            Calendar saved = calendarService.saveCalendarJson(
                    username, location, diaryText, imageUrl, date, createdAt
            );

            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("저장 실패: " + e.getMessage());
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserCalendars(@PathVariable String username) {
        return ResponseEntity.ok(calendarService.getUserCalendars(username));
    }
}