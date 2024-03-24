package rocketseat.company.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rocketseat.company.entities.Active;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    @NotBlank(message = "O campo [name] é obrigatório")
    private String name;

    @NotBlank(message = "O campo [category] é obrigatório")
    private String category;

    private Active active;
}
