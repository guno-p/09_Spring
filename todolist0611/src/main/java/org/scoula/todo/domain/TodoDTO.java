package org.scoula.todo.domain;

import lombok.Data;

@Data
public class TodoDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean done;
}
