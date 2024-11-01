package rocketseat.courses.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import rocketseat.courses.dto.CourseDTO;
import rocketseat.courses.entities.Active;
import rocketseat.courses.entities.CourseEntity;
import rocketseat.courses.exception.CourseException;
import rocketseat.courses.mapper.CourseMapper;
import rocketseat.courses.repository.CourseRepository;
import rocketseat.courses.services.CourseService;

import java.util.List;
import java.util.stream.Collectors;

import static rocketseat.courses.constants.Constants.COURSE_NOT_FOUND;
import static rocketseat.courses.constants.Constants.COURSE_NOT_FOUND_WITH_CATEGORY;
import static rocketseat.courses.constants.Constants.COURSE_NOT_FOUND_WITH_NAME;
import static rocketseat.courses.exception.ErrorTypeEnum.REFERENTIAL_INTEGRITY_FAILURE;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDTO insert(CourseDTO courseDTO) {
        CourseEntity courseEntity = courseMapper.toEntity(courseDTO);
        courseEntity = courseRepository.save(courseEntity);
        return courseMapper.toDto(courseEntity);
    }

    @Override
    public List<CourseDTO> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getById(Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseException(COURSE_NOT_FOUND));
        return CourseMapper.INSTANCE.toDto(course);
    }

    @Override
    public CourseDTO getByName(String name) {
        return courseRepository.buscarPorName(name.trim().toLowerCase())
                .map(courseMapper.INSTANCE::toDto)
                .orElseThrow(() -> new CourseException(COURSE_NOT_FOUND_WITH_NAME + name));
    }

    @Override
    public CourseDTO getByCategory(String category) {
        return courseRepository.findByCategory(category.trim().toLowerCase())
                .map(courseMapper.INSTANCE::toDto)
                .orElseThrow(() -> new CourseException(COURSE_NOT_FOUND_WITH_CATEGORY + category));
    }

    @Override
    public CourseDTO update(Long id, CourseDTO courseDTO) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseException(COURSE_NOT_FOUND));

        course.setName(courseDTO.getName());
        course.setCategory(courseDTO.getCategory());

        CourseEntity updateCourse = courseRepository.save(course);
        return CourseMapper.INSTANCE.toDto(updateCourse);
    }

    @Override
    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseException(COURSE_NOT_FOUND);
        }
        try {
            courseRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new CourseException(REFERENTIAL_INTEGRITY_FAILURE);
        }
    }

    @Override
    public CourseDTO toggleActive(Long id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseException(COURSE_NOT_FOUND));

        course.setActive(Active.NOT_ACTIVE);
        CourseEntity updateCourse = courseRepository.save(course);

        return courseMapper.toDto(updateCourse);
    }
}
