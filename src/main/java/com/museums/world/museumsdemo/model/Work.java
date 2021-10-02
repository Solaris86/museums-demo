package com.museums.world.museumsdemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Work {

    private Integer id;
    private String name;
    private String artist;
    private String period;
    private String medium;
    private Museum museum;

}
