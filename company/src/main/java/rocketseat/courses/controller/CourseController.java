package rocketseat.courses.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocketseat.courses.dto.CourseDTO;
import rocketseat.courses.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseDTO> insert(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.insert(courseDTO);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Long id) {
        CourseDTO course = courseService.getById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CourseDTO> getByName(@PathVariable String name) {
        CourseDTO course = courseService.getByName(name);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<CourseDTO> getByCategory(@PathVariable String category) {
        CourseDTO course = courseService.getByCategory(category);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateById(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.update(id, courseDTO);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CourseDTO> toggleActive(@PathVariable Long id) {
        CourseDTO updateCourse = courseService.toggleActive(id);
        return ResponseEntity.ok(updateCourse);
    }
}
