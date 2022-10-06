package sample.dao;

import java.math.BigDecimal;

import sample.model.Tbl2;
import sample.model.Tbl2_2;

public interface Tbl2Mapper {

	Tbl2 selectPk(int id);
	
	int selectPkCount(int id);
	
	String selectPkByName(int id);
	
	Tbl2_2 selectPkBigDecimal(BigDecimal id);

	int insert(Tbl2 tbl2);
	int updatePk(Tbl2 tbl2);
	int deletePk(int id);
	

}
