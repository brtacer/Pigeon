package com.berat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user_profile")
public class UserProfile extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60,nullable = false)
    private String nickname;
    private String headerPhoto;
    private String photo;
    @Column(length = 160)
    private String about;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "userProfile")
    private Location location;
    private String website;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserProfileStatus status = UserProfileStatus.ACTIVE;
    @Column(unique = true,length = 60,nullable = false)
    private String username;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userProfile")
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userProfile")
    private List<Post> posts;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userProfile")
    private List<Like> likes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "userProfile")
    private List<Share> shares;

}
