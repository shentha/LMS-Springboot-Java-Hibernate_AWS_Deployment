package com.numpyninja.lms.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {
	
	private Long assignmentId;

	private String assignmentName;
	
	private String assignmentDescription;

	private String comments;
	
	private Date dueDate;

	private String pathAttachment1;

	private String pathAttachment2;

	private String pathAttachment3;

	private String pathAttachment4;

	private String pathAttachment5;
		
	private String createdBy;
	
	//Batch entity details
	private Integer batchId;

	//Batch entity details
	private String graderId;
	

}
