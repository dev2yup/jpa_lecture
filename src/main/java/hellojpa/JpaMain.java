package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("hello");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member.getId());

            System.out.println("m = " + m.getTeam().getClass());

            System.out.println("====================");
            m.getTeam().getName();
            System.out.println("====================");

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