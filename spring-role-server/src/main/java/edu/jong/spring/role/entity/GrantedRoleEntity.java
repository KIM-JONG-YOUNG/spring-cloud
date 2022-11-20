package edu.jong.spring.role.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import edu.jong.spring.domain.model.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Entity
@IdClass(GrantedRoleEntity.PK.class)
@Table(name = "tb_granted_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GrantedRoleEntity extends BaseEntity {

	@Getter
	@Builder
	@EqualsAndHashCode
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	public static class PK implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private long roleNo;
		private long memberNo;
	} 

	@Id
	private long roleNo;

	@Id
	private long memberNo;

	@Builder
	public GrantedRoleEntity(long roleNo, long memberNo) {
		super();
		this.roleNo = roleNo;
		this.memberNo = memberNo;
	}
	
}
