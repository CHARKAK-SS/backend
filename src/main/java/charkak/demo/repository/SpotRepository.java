package charkak.demo.repository;

import charkak.demo.model.Spot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findByNameContaining(String keyword);

    // 등록 중복 확인
    @Query("SELECT s FROM Spot s WHERE REPLACE(s.name, ' ', '') = :name")
    List<Spot> findByNameIgnoreSpaces(@Param("name") String name);

    // 검색 공백 확인
    @Query("SELECT s FROM Spot s WHERE REPLACE(s.name, ' ', '') LIKE %:sanitized%")
    List<Spot> searchBySanitizedName(@Param("sanitized") String sanitized);
}

