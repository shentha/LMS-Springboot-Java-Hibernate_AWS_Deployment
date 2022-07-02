package com.numpyninja.lmstests.repotests;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.numpyninja.lms.LmsServicesApplication;
import com.numpyninja.lms.entity.Program;

import com.numpyninja.lms.repository.ProgramRepository;

//@SpringBootTest
//@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ContextConfiguration(classes=LmsServicesApplication.class)
public class ProgramRepoUnitTests {

	@Autowired
	private ProgramRepository progRepo;
	
	private Program newProg;
	
	@BeforeEach
    public void setup(){
		LocalDateTime now= LocalDateTime.now();
		Timestamp timestamp= Timestamp.valueOf(now);
		newProg= new Program((long) 7,"Django","new Prog", "nonActive",timestamp, timestamp);
	   }
	
	/*@AfterEach
	public void tearDown() {
		
	}*/
	
	
	
	@DisplayName("junit test for createProgram operation")
	@Test
	@Order(1)
	//@Rollback(value=false)
		public void testCreateProgram()
	{
		//Given -setup
		//when-action or behaviour that we are going to test
		Program saveEntity=progRepo.save(newProg);
		
		//then- verify the output
		assertNotNull(saveEntity);
		assertThat(newProg).isNotNull();
		
	assertThat(saveEntity.getProgramId()).isEqualTo(saveEntity.getProgramId());
	
	}
	
	//junit test for get list of programs from repo
	@DisplayName("junit test for getAllProgramList operation")
	@Test
	@Order(2)
	public void testGetAllPrograms()
	{
		//Given -setup
		Program initialSavedEntity=progRepo.save(newProg);
				
		//when-action or behaviour that we are going to test
		List<Program> listOfProgEntites=progRepo.findAll();
		
		//then- verify the output
		assertNotNull(listOfProgEntites);
		assertThat(listOfProgEntites.size()).isGreaterThan(0);
		
		Assertions.assertThat(listOfProgEntites).extracting(Program::getProgramName).containsAnyOf("Django");
	}
	
	//junit test for get program by programId from repo
	@DisplayName("junit test for get program by id")
	@Test
	@Order(3)
	public void testGetProgramById()
	{
		//Given-setup
		Program intialSavedEntity=progRepo.save(newProg);
		
		//when-action or behaviour 
		Program progEntity= progRepo.getById(intialSavedEntity.getProgramId());
		
		//then-verify
		assertNotNull(progEntity);
		assertThat(progEntity.getProgramId()).isEqualTo(intialSavedEntity.getProgramId());
	}
	
	//junit test for get program by programName from repo
	@DisplayName("junit test for get program(unique) by prograName")
	@Test
	@Order(4)
	public void testFindProgramByName() 
	{
	 //Given-setup
		Program initialSavedEntity=progRepo.save(newProg);
		
		//when-action or bhehaviour
		List<Program> progEntityList=progRepo.findByProgramName(initialSavedEntity.getProgramName());
		Program foundprogEntity =null;
		for(Program rec:progEntityList) {
			 if( rec.getProgramName().equalsIgnoreCase(initialSavedEntity.getProgramName())) {
			   foundprogEntity =rec;
			}
		  }
		
		//then-verify
				assertNotNull(progEntityList);
				assertThat(progEntityList.size()).isGreaterThan(0);
				assertNotNull(foundprogEntity);
		assertThat(foundprogEntity.getProgramName()).isEqualTo(newProg.getProgramName());
	}
	
	//junit test for update program by programid in repo
	@DisplayName("junit test for updating a rec in repo with progId")
	@Test
	@Order(5)
	//@Rollback(value=false)
	public void testUpdateByProgramId() {
		//Given-setup
		Program intialSavedEntity=progRepo.save(newProg);
		
		//when-action or behaviour
		Program progEntity= progRepo.getById(intialSavedEntity.getProgramId());
		progEntity.setProgramDescription("old course");
		progEntity.setProgramStatus("active");
		Program modifiedSavedEntity=progRepo.save(progEntity);
		
		//then-verify
		assertNotNull(modifiedSavedEntity);
		assertThat(modifiedSavedEntity.getProgramDescription()).isEqualTo(progEntity.getProgramDescription());
		assertThat(modifiedSavedEntity.getProgramStatus()).isEqualTo(progEntity.getProgramStatus());
	}
	
	//junit test for update program by programName in repo
	@Test
	@Order(6)
	//@Rollback(value=false)
	public void testUpdateByProgramName() {
		//Given-setup
		Program initialSavedProgramEntity =progRepo.save(newProg);
		
		//when-action or behaviour
		List<Program> progEntityList=progRepo.findByProgramName(initialSavedProgramEntity.getProgramName());
		Program foundprogEntity =null;
		for(Program rec:progEntityList) {
			 if( rec.getProgramName().equalsIgnoreCase(newProg.getProgramName())) {
			   foundprogEntity =rec;
			}
		  }
		foundprogEntity.setProgramDescription("old course");
		foundprogEntity.setProgramStatus("active");
		Program modifiedSavedEntity= progRepo.save(foundprogEntity);
		
		//then-verify
		assertNotNull(progEntityList);
		assertNotNull(foundprogEntity);
		assertNotNull(modifiedSavedEntity);
		assertThat(modifiedSavedEntity.getProgramDescription()).isEqualTo(foundprogEntity.getProgramDescription());
		assertThat(modifiedSavedEntity.getProgramStatus()).isEqualTo(foundprogEntity.getProgramStatus());
	}
	
//	//junit test for delete program by program Id from repo
//	@Test
//	@Order(7)
//	///@Rollback(value=false)
//	public void testDeleteByProgramId() {
//		//Given-setup
//	  Program initialSavedProgramEntity =progRepo.save(newProg);
//		
//	  //when-action or behaviour
//	  Program progEntity = progRepo.getById(initialSavedProgramEntity.getProgramId());
//	  progRepo.deleteById(progEntity.getProgramId());
//	  Optional<Program> programOptional= progRepo.findById(progEntity.getProgramId());
//	  //then-verify
//	  assertThat(programOptional).isEmpty();
//	}
//	
//	//junit test for delete program by programName from repo
//	@Test
//	@Order(8)
//	//@Rollback(value=false)
//	public void testDeleteByProgramName() {
//		//Given-setup
//		Program initialSavedProgramEntity =progRepo.save(newProg);
//	  
//	  //when-action or behaviour
//	   List<Program> progEntityList=progRepo.findByProgramName(initialSavedProgramEntity.getProgramName());
//		Program foundprogEntity =null;
//		for(Program rec:progEntityList) {
//			 if( rec.getProgramName().equalsIgnoreCase(newProg.getProgramName())) {
//			   foundprogEntity =rec;
//			}
//		  }
//		 progRepo.delete(foundprogEntity);
//		 Optional<Program> programOptional = progRepo.findById(foundprogEntity.getProgramId());
//		 
//		 //then-verify
//		 assertThat(programOptional).isEmpty();
//		
//	}
}
