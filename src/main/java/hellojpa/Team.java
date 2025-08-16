package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    // mappedBy로 연관관계 주인 지정(Member의 team이 연관관계 주인)
    // 주인이 아닌 쪽은 읽기만 가능
    @OneToMany(mappedBy = "team") // 양방향 매핑
    private List<Member> members = new ArrayList<>();

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
