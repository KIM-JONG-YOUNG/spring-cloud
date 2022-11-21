package edu.jong.spring.role.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.jong.spring.role.entity.GrantedRoleEntity;

public interface GrantedRoleRepository extends JpaRepository<GrantedRoleEntity, GrantedRoleEntity.PK> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT g FROM GrantedRoleEntity g WHERE g.roleNo = :roleNo AND g.memberNo = :memberNo")
	Optional<GrantedRoleEntity> findByIdForUpdate(
			@Param("roleNo") long roleNo, 
			@Param("memberNo") long memberNo);
}
