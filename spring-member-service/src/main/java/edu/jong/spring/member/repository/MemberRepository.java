package edu.jong.spring.member.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import edu.jong.spring.member.model.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	boolean existsByUsername(String username);
	
	Optional<MemberEntity> findByUsername(String username);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT m FROM MemberEntity m WHERE m.no = :no")
	Optional<MemberEntity> findByIdForUpdate(Long no);

}
