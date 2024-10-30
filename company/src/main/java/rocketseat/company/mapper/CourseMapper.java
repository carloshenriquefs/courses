package rocketseat.company.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rocketseat.company.dto.CourseDTO;
import rocketseat.company.entities.CourseEntity;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity toEntity(CourseDTO dto);

    CourseDTO toDto(CourseEntity entity);

}
