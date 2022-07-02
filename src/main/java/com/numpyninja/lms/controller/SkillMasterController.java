package com.numpyninja.lms.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.numpyninja.lms.dto.SkillMasterDto;
import com.numpyninja.lms.exception.DuplicateResourceFound;

import com.numpyninja.lms.exception.ResourceNotFoundException;

import com.numpyninja.lms.services.SkillMasterService;

@RestController
@RequestMapping
public class SkillMasterController {
	
	@Autowired
	SkillMasterService skillMasterService;

	//createSkills
	@PostMapping(path="/SaveSkillMaster",consumes = "application/json", produces = "application/json")  
	@ResponseBody
	private ResponseEntity<?> createAndSaveSkill(@Valid @RequestBody SkillMasterDto newSkillMaster)throws  DuplicateResourceFound
	{  
		SkillMasterDto savedSkillMasterDTO = skillMasterService.createAndSaveSkillMaster(newSkillMaster);
	return ResponseEntity.status(HttpStatus.CREATED).body(savedSkillMasterDTO);  
	} 
	
	//GetAllSkills
	@GetMapping(value = "/allSkillMaster")
	private ResponseEntity<?> getAllSkillsMaster()  throws ResourceNotFoundException
	{ 
		System.out.println("in getall SkillsMaster");
		List<SkillMasterDto> SkillsList = skillMasterService.getAllSkillMaster();
		return ResponseEntity.ok(SkillsList);  
	}  
	
	//GetSkillByName
	@GetMapping(path="skills/{skillMasterName}", produces = "application/json")  
	@ResponseBody
	private ResponseEntity <?> getOneSkillByName(@PathVariable("skillMasterName") @NotBlank @Positive String skillName)throws ResourceNotFoundException
	{  
		List<SkillMasterDto> skillmasterModelList= skillMasterService.getSkillMasterByName(skillName);
	return ResponseEntity.ok(skillmasterModelList);
	}  
	
	//UpdateSkillById
	@PutMapping(path="updateSkills/{skillId}", consumes = "application/json", produces = "application/json")  
	@ResponseBody
	private ResponseEntity <SkillMasterDto> updateSkillById(@PathVariable @NotBlank @Positive Long skillId ,@Valid @RequestBody SkillMasterDto modifySkillMaster) throws ResourceNotFoundException
	{  
	return ResponseEntity.ok(skillMasterService.updateSkillMasterById(skillId,modifySkillMaster));
	} 
	
	//DeleteSkillsById
	@DeleteMapping(path="deletebySkillId/{skillId}",produces = "application/json")  
	@ResponseBody
	private ResponseEntity<?>  deleteBySkillId(@PathVariable("skillId")@NotBlank @Positive Long skillId) throws ResourceNotFoundException  
	{  
	System.out.println("in delete by SkillId controller");
	boolean deleted = skillMasterService.deleteBySkillId(skillId); 
	if(deleted)
		return ResponseEntity.status(HttpStatus.OK).build();
			else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}  
	
}
