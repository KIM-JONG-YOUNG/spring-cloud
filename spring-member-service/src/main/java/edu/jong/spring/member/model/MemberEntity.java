package edu.jong.spring.member.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.jong.spring.domain.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "tb_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@NotNull
	@Size(max = 30)
	@Column(unique = true)
	private String username;
	
	@Setter
	@NotNull
	@Size(max = 60)
	private String password;
	
	@Setter
	@NotNull
	@Size(max = 30)
	private String name;
	
	@Setter
	@NotNull
	@Convert(converter = Gender.AttributeConverter.class)
	@Column(length = 1)
	private Gender gender;
	
	@Setter
	@NotNull
	@Email
	@Size(max = 60)
	private String email;

	@Builder
	public MemberEntity(
			String username, 
			String password,
			String name, 
			Gender gender,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}
	
	
}
