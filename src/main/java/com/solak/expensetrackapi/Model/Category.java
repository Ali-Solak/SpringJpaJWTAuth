package com.solak.expensetrackapi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "et_categories")
@Table(name = "et_categories")
public class Category {

    @Id
    @SequenceGenerator(name = "et_category_seq", sequenceName = "et_category_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "et_category_seq")
    @Column(name = "category_id")
    private Long categoryId;
    private String title;
    private String description;
    private Double totalExpense;
    private String password;

    @ManyToMany(mappedBy = "categories")
    private Set<Et_Users> users = new HashSet<>();
}
