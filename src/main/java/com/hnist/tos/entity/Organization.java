package com.hnist.tos.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Pany
 * @date 2020-04-21 19:59
 * @content
 */
@Data
@Entity
@Table(name = "t_organization")
public class Organization {

    /**
     * 机构ID（自动增长）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     *机构名称
     */
    @Column(length = 32, nullable = false)
    private String name;

    /**
     * 机构描述
     */
    @Column(length = 100)
    private String description;

    /**
     * 机构创建时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp createdTime;

    /**
     * 机构修改时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    private Timestamp updatedTime;
}
