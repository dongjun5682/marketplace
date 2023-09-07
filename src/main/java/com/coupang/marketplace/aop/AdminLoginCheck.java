package com.coupang.marketplace.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface AdminLoginCheck {
  
}
