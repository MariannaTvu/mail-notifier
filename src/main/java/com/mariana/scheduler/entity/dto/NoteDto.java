package com.mariana.scheduler.entity.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by nicot on 1/6/2018.
 */
@ApiModel
@Getter
@Setter
public class NoteDto {
    private final String text;
    private final String title;
    private final String expireDate;
    private final Long userId;

    @JsonCreator
    public NoteDto(@JsonProperty("user_id") Long userId,
                   @JsonProperty("title") String title,
                   @JsonProperty("text") String text,
                   @JsonProperty("expire_date") String expireDate){
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.expireDate = expireDate;
    }
}
