package com.example.web.BackEnd.RestApi.models;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@RequiredArgsConstructor
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "available")
    private boolean available;

    //@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CategoryModel> categories = new HashSet<>();

}
