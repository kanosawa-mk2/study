package sample.dao;

import java.math.BigDecimal;

import sample.model.Tbl2;
import sample.model.Tbl2_2;

public interface Tbl2Mapper {

	Tbl2 selectPk(int id);
	
	int selectPkCount(int id);
	
	String selectPkByName(int id);
	
	Tbl2_2 selectPkBigDecimal(BigDecimal id);
	
	/**
	 * パラメータが２つ以上の場合は@Paramを付与する。<br>
	 * 何故か無くても動くが。。dbの違いか。。？
	 * @param id
	 * @param name
	 * @return
	 */
	//Tbl2 selectWhereDouble(@Param("id") int id,@Param("name") String name);
	Tbl2 selectWhereDouble(int id,String name);
	
	int insert(Tbl2 tbl2);
	int updatePk(Tbl2 tbl2);
	int deletePk(int id);
	

}
