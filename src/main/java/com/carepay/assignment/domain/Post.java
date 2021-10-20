package com.carepay.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "post")
public class Post extends Audit {
//    schema="schema"
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", columnDefinition="VARCHAR(128)")
    private String title;

    @Column(name = "content", columnDefinition="VARCHAR(128)")
    private String content;
}
