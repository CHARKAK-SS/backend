package charkak.demo.controller;

import charkak.demo.model.Spot;
import charkak.demo.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/spots")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping("/search")
    public List<Spot> search(@RequestParam String keyword) {
        return spotService.searchByKeyword(keyword);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String address = body.get("address");

        try {
            Spot spot = spotService.createSpot(name, address);
            return ResponseEntity.status(HttpStatus.CREATED).body(spot);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public List<Spot> findAll() {
        return spotService.findAll();
    }
}
