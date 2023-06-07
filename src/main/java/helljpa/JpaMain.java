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
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");

//            JPA의 변경 감지라는 기능 덕분에 아래를 하지 않아도 됨 트랜잭션이 끝나면 변경을 감지해서 자동 커밋
//            em.persist(member);
            System.out.println("-=============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
