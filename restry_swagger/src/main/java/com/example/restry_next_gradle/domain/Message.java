package com.example.restry_next_gradle.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table
@ToString(of = {"id", "text", "tag"})
@EqualsAndHashCode(of = {"id"})
@ApiModel(description = "Class representing a message with tag.")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Unique identifier of the message. No two messages can have the same id. Generated automatically on creation", example = "1", position = 0, hidden = true)
    private Long id;

    @Column(columnDefinition="text")
    @NotBlank
    @ApiModelProperty(notes = "Text of the message.", example = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Est nihil quia at a rem laudantium vero, praesentium sint odit deserunt earum consectetur ut cupiditate dolor similique eum quaerat nobis nam.",required = true, position = 1)
    private String text;
    @ApiModelProperty(notes = "Tag of the message.", example = "new", position = 2)
    private String tag;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "Creation date & time of the message. Generated automatically on creation.", example = "1990-01-20 23:59:27", position = 4, hidden = true)
    private LocalDateTime creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
