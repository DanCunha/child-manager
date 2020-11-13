package br.com.child.manager.dto;

import br.com.child.manager.domain.Child;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildDTO {

    private Long id;

    private String name;

    private LocalDate dateBirth;

    public ChildDTO(Child child){
        this.id = child.getId();
        this.name = child.getName();
        this.dateBirth = child.getDateBirth();
    }
}
