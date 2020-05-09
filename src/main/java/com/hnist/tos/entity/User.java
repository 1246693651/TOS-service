package com.hnist.tos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.crazycake.shiro.AuthCachePrincipal;
import org.hibernate.annotations.GenerationTime;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_users")
public class User implements Serializable, AuthCachePrincipal {
    /**
     * 用户ID，系统自动生成，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    /**
//     * 用户名
//     */
//    @Column(length = 64, nullable = false)
//    private String username;
//
//    /**
//     * 用户密码
//     */
//    @Column(length = 64, nullable = false)
//    private String password;

    /**
     * 用户昵称
     */
    @Column(length = 64)
    private String nickname;

    /**
     * 用户头像URL，默认微信头像
     */
    @Column(length = 512)
    private String avatar;

    /**
     * 小程序用户的openID
     */
    @Column(length = 128,nullable = false,unique = true)
    private String openId;

    /**
     * 小程序unionID
     */
    @Column(length = 128)
    private String unionId;

    /**
     * 用户创建时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private Timestamp createdTime;

    /**
     * 用户修改时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    private Timestamp updatedTime;

    /**
     * JsonIgnore:忽略json转化
     * 用户权限（多对多）
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "t_user_role",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<Role>(0);

    @Override
    public String getAuthCacheKey() {
        return null;
    }
}
