package rocketseat.courses.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rocketseat.courses.dto.CourseDTO;
import rocketseat.courses.entities.CourseEntity;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity toEntity(CourseDTO dto);

    CourseDTO toDto(CourseEntity entity);

}
