package com.backend.hydrominder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoseDTO {

    private Integer id;

    private Integer dose;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    private String message;

    public DoseDTO() {}

    public DoseDTO(Integer id, Integer dose, Date date) {
        this.id = id;
        this.dose = dose;
        this.date = date;
    }

    public DoseDTO(String message) {
        this.message = message;
    }
}
