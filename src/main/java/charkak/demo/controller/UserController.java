package charkak.demo.controller;

import charkak.demo.model.User;
import charkak.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        String username = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("error", "사용자를 찾을 수 없습니다"));
        }

        return ResponseEntity.ok(Map.of(
                "name", user.getName()  // ✅ name 필드 반환
        ));
    }
}
