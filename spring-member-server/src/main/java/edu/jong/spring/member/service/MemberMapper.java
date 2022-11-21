package edu.jong.spring.member.service;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.jong.spring.member.entity.MemberEntity;
import edu.jong.spring.member.request.MemberJoinParam;
import edu.jong.spring.member.request.MemberModifyParam;
import edu.jong.spring.member.response.MemberDetails;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {

	@Mapping(target = "username", source = "param.username")
	@Mapping(target = "password", 
		expression = "java(encoder.encode(param.getPassword()))")
	@Mapping(target = "name", source = "param.name")
	@Mapping(target = "gender", source = "param.gender")
	@Mapping(target = "email", source = "param.email")
	MemberEntity toEntity(MemberJoinParam param, PasswordEncoder encoder);
	
	MemberDetails toDetails(MemberEntity entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	MemberEntity updateEntity(MemberModifyParam param, @MappingTarget MemberEntity entity);

}
