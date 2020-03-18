package com.melelee.melelee.config;

import java.lang.annotation.*;

/**
 * @author by melelee
 * @date 2020/3/16 10:12
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnotation {
}
