package com.museums.world.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Museum {

    private Integer id;

    @NotBlank
    @Length(min = 1, max = 100)
    private String name;

    @NotBlank
    @Length(min = 1, max = 100)
    private String address;

    @NotBlank
    @Length(min = 1, max = 50)
    private String phone;

    @Length(max = 4)
    private String founded;

    @Valid
    private List<Work> works;

}
