package com.dorefactor.todoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Task {

    private final long id;
    private final String name;
    private final String description;

}
