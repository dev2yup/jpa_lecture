package hellojpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    private Team team;

    // 대상 테이블에 외래 키 단방향은 지원 X
    @OneToOne // 일대일 양방향 (외래키가 있는 곳이 연관관계 주인)
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) { // 연관관계 편의 메서드 1에 만들어도 되고 N에 넣어도 됨
        this.team = team;
        team.getMembers().add(this);
    }
}
