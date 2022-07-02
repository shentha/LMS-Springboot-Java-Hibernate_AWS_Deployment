package com.numpyninja.lms.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillMasterModel {
	private Long skillId;
	private String skillName;
	private Timestamp creationTime;
	private Timestamp lastModTime;
}
