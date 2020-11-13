package com.example.demo.domain;


import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String title;
    private String iban;
    private String author;
}
