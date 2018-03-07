package com.yuchen.base.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	
	/**
	 * The attribute name of the table in the database.
	 */
	public String name();
	
	/**
	 * Can it be empty?
	 */
	public boolean nullable() default true;
	
	/**
	 * Whether the primary key?
	 */
	public boolean primaryKey() default false;
		
}
