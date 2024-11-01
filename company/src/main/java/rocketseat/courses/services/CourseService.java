package rocketseat.courses.services;

import org.springframework.stereotype.Service;
import rocketseat.courses.dto.CourseDTO;

import java.util.List;

@Service
public interface CourseService {

    CourseDTO insert(CourseDTO courseEntity);

    List<CourseDTO> getAll();

    CourseDTO getById(Long id);

    CourseDTO getByName(String name);

    CourseDTO getByCategory(String category);

    CourseDTO update(Long id, CourseDTO courseDTO);

    void delete(Long id);

    CourseDTO toggleActive(Long id);

}
