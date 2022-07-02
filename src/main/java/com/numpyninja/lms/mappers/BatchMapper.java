package com.numpyninja.lms.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.numpyninja.lms.dto.BatchDTO;
//import com.numpyninja.lms.dto.BatchDto;
import com.numpyninja.lms.entity.Batch;



@Mapper(componentModel = "spring", uses=ProgramMapper.class)
public interface BatchMapper {
	BatchMapper INSTANCE = Mappers.getMapper(BatchMapper.class);
	
	@Mapping ( source = "batch.program.programId", target = "programId")
	@Mapping ( source = "batch.program.programName", target = "programName")
	BatchDTO toBatchDTO(Batch batch);
	
	@Mapping ( source = "dto.programId", target = "program.programId" )
 	Batch  toBatch ( BatchDTO dto);
	 
	List<BatchDTO> toBatchDTOs(List<Batch> baches);
}
