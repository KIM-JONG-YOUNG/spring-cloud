package edu.jong.spring.role.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import edu.jong.spring.domain.model.BaseEntity;
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
@Table(name = "tb_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@NotNull
	@Size(max = 30)
	@Pattern(regexp = "^ROLE_[A-Z]+")
	@Column(unique = true)
	private String name;

	@Setter
	@NotNull
	@Convert(converter = APIMethod.AttributeConverter.class)
	private APIMethod accessibleMethod;
	
	@Setter
	@NotNull
	@Size(max = 60)
	@AntPattern
	private String accessibleUrlPattern;

	@Builder
	public RoleEntity(String name, APIMethod accessibleMethod, String accessibleUrlPattern) {
		super();
		this.name = name;
		this.accessibleMethod = accessibleMethod;
		this.accessibleUrlPattern = accessibleUrlPattern;
	}
	
}
