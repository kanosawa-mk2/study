package main;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.Tbl2;

public class HibernateTest {
	
	private static final String PERSISTENCE_UNIT_NAME = "hibernate-jpa-sample";

	public static void main(String[] args) {
		
		// EntityManagerのオブジェクトを生成
        EntityManager em =  Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();;

        // 1件取得して名前を表示する
        Tbl2 city = em.find(Tbl2.class, 1);
        System.out.println(city.getName());

        // EntityManagerの利用を終了する
        em.close();

	}

}
