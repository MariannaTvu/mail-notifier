package com.mariana.scheduler.entity.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by nicot on 1/6/2018.
 */
@ApiModel
public class NoteDto {
    private String text;

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

    private String title;

    private String expireDate;

    private Long userId;

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public Long getUserId() {
        return userId;
    }
}
