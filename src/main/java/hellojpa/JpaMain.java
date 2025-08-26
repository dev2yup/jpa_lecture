package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 1L);
            printMemberAndTeam(member);

            em.persist(member);

            em.flush();
            em.clear();

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
    
    public static void printMember(Member member) {
        System.out.println("member.getUsername() = " + member.getUsername());
    }

    public static void printMemberAndTeam(Member member)  {
        String userName = member.getUsername();
        System.out.println("userName = " + userName);
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }
}