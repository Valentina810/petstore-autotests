package com.github.valentina810.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Category {
    private int id;
    private String name;

    public Category deepCopy() {
        return Category.builder()
                .id(this.id)
                .name(this.name).build();
    }
}