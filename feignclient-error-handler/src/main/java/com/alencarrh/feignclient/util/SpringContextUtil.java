package com.alencarrh.feignclient.util;

import static java.util.Optional.ofNullable;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

  private static ApplicationContext CONTEXT;

  @Override
  public void setApplicationContext(final ApplicationContext applicationContext)
      throws BeansException {
    CONTEXT = applicationContext;
  }

  public static <T> T getBean(final Class<T> beanClass) {
    return ofNullable(CONTEXT).map(ctx -> ctx.getBean(beanClass)).orElse(null);
  }
}
