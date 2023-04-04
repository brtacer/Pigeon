package com.berat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_post")
public class Post extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(nullable = false)
    private UserProfile userProfile;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Like> likes;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Share> shares;
}
