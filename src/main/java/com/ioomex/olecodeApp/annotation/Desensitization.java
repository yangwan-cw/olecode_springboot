package com.ioomex.olecodeApp.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ioomex.olecodeApp.common.DesensitizationStrategy;
import com.ioomex.olecodeApp.config.DesensitizationJsonSerializable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = DesensitizationJsonSerializable.class)
public @interface Desensitization {
    /**
     * 脱敏策略
     *
     * @return
     */
    DesensitizationStrategy value();
}