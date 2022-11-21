package edu.jong.spring.role.service;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import edu.jong.spring.role.entity.RoleEntity;
import edu.jong.spring.role.request.RoleAddParam;
import edu.jong.spring.role.request.RoleModifyParam;
import edu.jong.spring.role.response.RoleDetails;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, 
		unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

	RoleEntity toEntity(RoleAddParam param);
	
	RoleDetails toDetails(RoleEntity entity);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	RoleEntity updateEntity(RoleModifyParam param, @MappingTarget RoleEntity entity);

}
