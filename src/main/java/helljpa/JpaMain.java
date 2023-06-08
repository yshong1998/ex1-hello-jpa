package helljpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("======================");

            //DB SEQ = 1   |   1
            //DB SEQ = 51  |   2
            //DB SEQ = 51  |   3

            em.persist(member1); //1 -> 1이면 다음 시퀀스가 필요한 상태니까 한 번 더 호출 -> 51로 한 번 더 증가
            em.persist(member2); //MEM DB의 시퀀스 값은 그대로 51이고 메모리에서는 증가
            em.persist(member3); //MEM
            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());
            System.out.println("======================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
