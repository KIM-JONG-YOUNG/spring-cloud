package edu.jong.spring.role.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import edu.jong.spring.common.constants.Patterns;
import edu.jong.spring.common.constants.TableNames;
import edu.jong.spring.domain.converter.AbstractAttributeConverter;
import edu.jong.spring.domain.entity.BaseEntity;
import edu.jong.spring.role.enums.APIMethod;
import edu.jong.spring.role.validate.AntPattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Entity
@Table(name = TableNames.TB_ROLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@NotNull
	@Pattern(regexp = Patterns.ROLE_NAME)
	@Size(max = 30)
	@Column(unique = true)
	private String name;

	@Setter
	@NotNull
	@Convert(converter = APIMethodAttributeConverter.class)
<<<<<<< HEAD
	@Column(length = 6)
=======
	@Column(length = 5)
>>>>>>> branch 'master' of https://github.com/KIM-JONG-YOUNG/spring-cloud.git
	private APIMethod accessibleMethod;
	
	@Setter
	@NotNull
	@AntPattern
	@Size(max = 60)
	private String accessibleUrlPattern;

	@Builder
	public RoleEntity(String name, APIMethod accessibleMethod, String accessibleUrlPattern) {
		super();
		this.name = name;
		this.accessibleMethod = accessibleMethod;
		this.accessibleUrlPattern = accessibleUrlPattern;
	}
	
	@Converter
<<<<<<< HEAD
	public static class APIMethodAttributeConverter extends AbstractAttributeConverter<APIMethod, String>{
=======
	public class APIMethodAttributeConverter extends AbstractAttributeConverter<APIMethod, String>{
>>>>>>> branch 'master' of https://github.com/KIM-JONG-YOUNG/spring-cloud.git
		public APIMethodAttributeConverter() {
			super(APIMethod.class, false);
		}
	}
}
