package br.com.child.manager.form;

import br.com.child.manager.domain.Child;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChildForm {

    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateBirth;

    public Child converter(){
        return new Child(name, dateBirth);
    }
}
