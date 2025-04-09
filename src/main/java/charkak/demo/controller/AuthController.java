package charkak.demo.controller;
import java.util.Map;

import charkak.demo.dto.LoginRequestDto;
import charkak.demo.dto.RegisterRequestDto;
import charkak.demo.model.User;
import charkak.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto) {
        try {
            User user = userService.registerUser(dto.getUsername(), dto.getName(), dto.getPassword());
            return ResponseEntity.ok("회원가입 성공: " + user.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("회원가입 실패: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {
        try {
            String token = userService.login(dto.getUsername(), dto.getPassword());
            return ResponseEntity.ok("JWT 토큰: " + token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("로그인 실패: " + e.getMessage());
        }
    }

    @PostMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("아이디를 입력해주세요.");
        }

        boolean exists = userService.existsByUsername(username);
        if (exists) {
            return ResponseEntity.status(409).body("이미 존재하는 아이디입니다.");
        }

        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }


}
