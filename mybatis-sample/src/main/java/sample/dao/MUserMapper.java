package sample.dao;

import sample.model.MUser;

public interface MUserMapper {
	
	MUser selectPk(String userId);

}
