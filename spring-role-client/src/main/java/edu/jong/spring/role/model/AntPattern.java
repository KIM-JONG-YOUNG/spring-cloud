package edu.jong.spring.role.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.util.AntPathMatcher;

@Retention(RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Constraint(validatedBy = AntPattern.Validator.class)
public @interface AntPattern {

	String message() default "Ant Pattern에 맞지 않습니다.";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	public static class Validator implements ConstraintValidator<AntPattern, String> {

		private static final AntPathMatcher MATCHER = new AntPathMatcher();
		
		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			return (value != null) ? MATCHER.isPattern(value) : true;
		}
		
	}
}
