package rocketseat.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rocketseat.courses.entities.CourseEntity;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    Optional<CourseEntity> findByName(String name);

    Optional<CourseEntity> findByCategory(String category);

    @Query("SELECT c FROM CourseEntity c WHERE c.name = :name")
    Optional<CourseEntity> buscarPorName(String name);

    @Query("SELECT c FROM CourseEntity c WHERE c.category = :category")
    Optional<CourseEntity> buscarPorCategory(String category);
}
