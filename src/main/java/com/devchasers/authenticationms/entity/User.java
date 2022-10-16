package com.devchasers.authenticationms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Document("Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true, background = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;





//    @OneToMany(mappedBy = "user")
//    private List<Notification> notifications;
//
//    @OneToMany(mappedBy = "primaryKey.user",
//            cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({ "user"})
//    private Set<UserProject> userProjects ;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinTable(name = "USER_ROLES", joinColumns = {
//            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID") })
//    @JsonIgnoreProperties("users")
//    private List<Role> roles = new ArrayList<>();




//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("user")
//    private List<Request> requests;


}
