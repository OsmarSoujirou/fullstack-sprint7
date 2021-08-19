package br.com.rchlo.store.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String profile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="profile_users",
                joinColumns = {@JoinColumn(name="profile_id")},
                inverseJoinColumns = {@JoinColumn(name="user_id")})
    private List<Users> users = new ArrayList<Users>();

    @Override
    public String getAuthority() {
        return this.profile;
    }
}
