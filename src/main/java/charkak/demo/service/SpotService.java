package charkak.demo.service;

import charkak.demo.model.Spot;
import charkak.demo.repository.SpotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public List<Spot> searchByKeyword(String keyword) {
        String sanitized = keyword.replaceAll("\\s+", "");
        return spotRepository.searchBySanitizedName(sanitized);
    }

    public Spot createSpot(String name, String address) {
        String sanitizedInputName = name.replaceAll("\\s+", "");

        List<Spot> existing = spotRepository.findByNameIgnoreSpaces(sanitizedInputName);
        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("이미 동일한 장소명이 존재합니다.");
        }

        Spot spot = Spot.builder()
                .name(name)
                .address(address)
                .build();
        return spotRepository.save(spot);
    }

    public List<Spot> findAll() {
        return spotRepository.findAll();
    }
}
