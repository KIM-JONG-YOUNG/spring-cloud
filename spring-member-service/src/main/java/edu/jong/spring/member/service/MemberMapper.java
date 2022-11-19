package edu.jong.spring.member.service;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import edu.jong.spring.member.model.MemberDetails;
import edu.jong.spring.member.model.MemberEntity;
import edu.jong.spring.member.model.MemberJoinParam;
import edu.jong.spring.member.model.MemberModifyParam;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

	MemberEntity toEntity(MemberJoinParam param);
	
	MemberDetails toDetails(MemberEntity entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	MemberEntity updateEntity(MemberModifyParam param, @MappingTarget MemberEntity entity);
	
}
