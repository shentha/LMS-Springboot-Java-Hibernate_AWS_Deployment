package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.numpyninja.lms.dto.AssignmentDto;
import com.numpyninja.lms.entity.Assignment;

@Mapper(componentModel = "spring", uses={BatchMapper.class, UserMapper.class})
public interface AssignmentMapper {
 
    AssignmentMapper INSTANCE = Mappers.getMapper(AssignmentMapper.class);

	@Mapping(source="assignment.batch.batchId", target="batchId")
	@Mapping(source="assignment.user.userId", target="graderId")
    AssignmentDto toAssignmentDto(Assignment assignment); 
    
	@InheritInverseConfiguration
	Assignment toAssignment(AssignmentDto assignmentDto);
	
	List<AssignmentDto> toAssignmentDtoList(List<Assignment> assignments);
	 
	List<Assignment> toAssignmentList(List<AssignmentDto> AssignmentDtos);
}
