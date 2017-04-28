package com.example.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class AppMain {
    public static void main( String[] args ) {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("java-jpa-example");
    	EntityManager em = emf.createEntityManager();
    	EntityTransaction tx = em.getTransaction();	//트랜젝션 기능획득 
    	
    	try {
    		tx.begin();
    		logic(em);
    		tx.commit();
    	} catch (Exception e) {
    		e.printStackTrace();
    		tx.rollback();
    	} finally {
    		em.close();
    	}
    	emf.close();
    }
    
    private static void logic(EntityManager em) {
    	String id = "id1";
    	Member member = new Member();
    	member.setId(id);
    	member.setName("홍길동");
    	member.setAge(2);
    	
    	//등록 
    	em.persist(member);
    	
    	//수정 
    	member.setAge(20);
    	
    	//한건 조회 
    	Member findMember = em.find(Member.class, id);
    
    	System.out.println("findMember = " + findMember.getName() + ", age = " + findMember.getAge());
    	
    	Member findMember2 = em.find(Member.class, id);
    	System.out.println(">>>> "+(findMember == findMember2));
    	System.out.println("findMember2 = " + findMember2.getName() + ", age = " + findMember2.getAge());
    	
    	//목록 조회 
    	List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
    	System.out.println("members.size = " + members.size());
    	
    	//삭제 
    	em.remove(member);
    	
    }
}
