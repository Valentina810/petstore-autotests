package com.github.valentina810.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Pet {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public Pet deepCopy() {
        return Pet.builder()
                .id(this.id)
                .category(this.category != null ? this.category.deepCopy() : null)
                .name(this.name)
                .photoUrls(this.photoUrls != null ? new ArrayList<>(this.photoUrls) : null)
                .tags(this.tags != null ? this.tags.stream().map(Tag::deepCopy).collect(toList()) : null)
                .status(this.status)
                .build();
    }
}