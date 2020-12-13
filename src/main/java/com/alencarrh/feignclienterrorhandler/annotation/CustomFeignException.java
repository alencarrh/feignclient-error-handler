package com.alencarrh.feignclienterrorhandler.annotation;

import com.alencarrh.feignclienterrorhandler.exception.FeignClientErrorException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Function;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CustomFeignException {

  Class<? extends Function<FeignClientErrorException, RuntimeException>> handler();
}
