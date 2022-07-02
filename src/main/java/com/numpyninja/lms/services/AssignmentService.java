package com.numpyninja.lms.services;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numpyninja.lms.dto.AssignmentDto;
import com.numpyninja.lms.entity.Assignment;
import com.numpyninja.lms.entity.Batch;
import com.numpyninja.lms.exception.DuplicateResourceFoundException;
import com.numpyninja.lms.exception.ResourceNotFoundException;
import com.numpyninja.lms.mappers.AssignmentMapper;
import com.numpyninja.lms.repository.AssignmentRepository;
import com.numpyninja.lms.repository.ProgBatchRepository;



@Service
public class AssignmentService {
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private ProgBatchRepository batchRepository;
	
	@Autowired
	private AssignmentMapper assignmentMapper;
		
	//create an assignment 
	public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
		 Optional<Assignment> savedAssignment = this.assignmentRepository
				 .findByAssignmentName(assignmentDto.getAssignmentName());
		 if(savedAssignment.isPresent()) {
			 throw new DuplicateResourceFoundException("Assignment", 
				 "Name", assignmentDto.getAssignmentName());
		 }
		 Assignment assignment = assignmentMapper.toAssignment(assignmentDto);
		 LocalDateTime now= LocalDateTime.now();
		 Timestamp timestamp= Timestamp.valueOf(now);
		 assignment.setCreationTime(timestamp);
		 assignment.setLastModTime(timestamp);
		 Assignment newAssignment = this.assignmentRepository.save(assignment);
		 return assignmentMapper.toAssignmentDto(newAssignment);
	}
	
	//update an assignment
	public AssignmentDto updateAssignment(AssignmentDto assignmentDto, Long assignmentId) {
		Assignment savedAssignment = this.assignmentRepository.findById(assignmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment", "Id", assignmentId));
		Assignment updateAssignment = assignmentMapper.toAssignment(assignmentDto);
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		updateAssignment.setAssignmentId(assignmentId);
		updateAssignment.setCreationTime(savedAssignment.getCreationTime()); 
		updateAssignment.setLastModTime(timestamp); 
		Assignment updatedAssignment = this.assignmentRepository.save(updateAssignment);
		AssignmentDto updatedAssignmentDto = assignmentMapper.toAssignmentDto(updatedAssignment);
		return updatedAssignmentDto;
		
	}
	
	//delete an assignment
	public void deleteAssignment(Long id) {
		Assignment assignment = this.assignmentRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Assignment", "Id", id));
		this.assignmentRepository.deleteById(id);
	}

	// get all assignments
    public List<AssignmentDto> getAllAssignments() {
    	List<Assignment> assignments =  this.assignmentRepository.findAll();
    	//List<AssignmentDto> assignmentDtos = assignments.stream()
    	//		.map(assignment -> assignmentMapper.toAssignmentDto(assignment)).collect(Collectors.toList());
		List<AssignmentDto> assignmentDtos = assignmentMapper.toAssignmentDtoList(assignments);
    	return assignmentDtos;
    }

	//get assignment by id
	public AssignmentDto getAssignmentById(Long id) {
		Assignment assignment = this.assignmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment", "Id", id));
		return assignmentMapper.toAssignmentDto(assignment);
	}
	
	//get assignments for a batch
	public List<AssignmentDto> getAssignmentsForBatch(Integer batchId) {
		Batch batch = this.batchRepository.findById(batchId)
				.orElseThrow(() -> new ResourceNotFoundException("Batch", "Id", batchId));
		List<Assignment> assignments = this.assignmentRepository.findByBatch(batch);
		List<AssignmentDto> assignmentDtos = assignmentMapper.toAssignmentDtoList(assignments);
		return assignmentDtos;
	}
}
