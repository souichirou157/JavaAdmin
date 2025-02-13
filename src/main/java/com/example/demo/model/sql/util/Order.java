package com.example.demo.model.sql.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//どのタイミングで実行するか
//どこまでアノテーションを有効にするかを決める
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD)
public @interface Order {
	int order();
}
