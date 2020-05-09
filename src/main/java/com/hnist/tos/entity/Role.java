package com.hnist.tos.entity;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_role")
public class Role {

    /**
     * 角色ID（自动增长）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *角色名称
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * 角色描述
     */
    @Column(length = 100)
    private String description;

    /**
     * 角色创建时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp createdTime;

    /**
     * 角色修改时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp updatedTime;

    /**
     * 该角色下的用户
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles") //不维护中间表
    private Set<User> users= new HashSet<>(0);

}
