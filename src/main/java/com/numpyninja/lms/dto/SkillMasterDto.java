package com.numpyninja.lms.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillMasterDto {
	private Long skillId;
	private String skillName;
	private Timestamp creationTime;
	private Timestamp lastModTime;
}
