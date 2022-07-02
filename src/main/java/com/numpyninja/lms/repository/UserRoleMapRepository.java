package com.numpyninja.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.numpyninja.lms.entity.User;
import com.numpyninja.lms.entity.UserRoleMap;

@Repository
public interface UserRoleMapRepository  extends JpaRepository <UserRoleMap, Long>{
	List<UserRoleMap> findUserRoleMapsByRoleRoleName( String roleName );
	
	List<UserRoleMap> findUserRoleMapsByBatchesProgramProgramId( Long programId ) ;
	
	List<UserRoleMap> findUserRoleMapsByUserUserId(String userId );
        
}


