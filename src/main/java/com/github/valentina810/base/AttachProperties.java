package com.github.valentina810.base;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AttachProperties {
    private String attach;
    private String stepName;
    private String attachName;
}