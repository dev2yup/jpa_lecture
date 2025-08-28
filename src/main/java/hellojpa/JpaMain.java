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
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamA");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("hello");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m = em.find(Member.class, member.getId());

            em.createQuery("select m from Member m", Member.class)
                            .getResultList();

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