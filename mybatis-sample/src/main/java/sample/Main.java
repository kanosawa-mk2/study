package sample;

import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import sample.dao.MUserMapper;
import sample.dao.Tbl2Mapper;
import sample.model.Tbl2;

public class Main {

	public static void main(String[] args) {

		String resource = "/mybatis-config.xml";
		InputStream inputStream = Main.class.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		try (SqlSession session = sqlSessionFactory.openSession()) {
			Tbl2Mapper mapper = session.getMapper(Tbl2Mapper.class);
			System.out.println(mapper.selectPk(1));
			System.out.println(mapper.selectPkCount(1));
			System.out.println(mapper.selectPkBigDecimal(BigDecimal.ONE));
			
			Tbl2 tbl = new Tbl2();
			tbl.setId(99);
			tbl.setName("テスト 九九");
			System.out.println(mapper.insert(tbl));
			
			tbl.setName("テスト 九九-二");
			System.out.println(mapper.updatePk(tbl));
			
			System.out.println(mapper.deletePk(99));
			
			MUserMapper usermapper = session.getMapper(MUserMapper.class);
			
			
			System.out.println(usermapper.selectPk("001"));
			
			
			session.commit();
		}

	}

}
