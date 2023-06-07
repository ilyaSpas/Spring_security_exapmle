package com.ilsy.Security.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private String dateOfCreate;


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private Post post;

    private String author;

//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinColumn
//    private User user;

    @PrePersist
    private void init(){
        DateFormat SimpleDate = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        Date date = new Date();
        dateOfCreate = SimpleDate.format(date);
    }
}
