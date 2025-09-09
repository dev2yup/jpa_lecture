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
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
            em.remove(findParent);

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