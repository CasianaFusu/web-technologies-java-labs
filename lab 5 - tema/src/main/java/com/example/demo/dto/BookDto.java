package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    @NotNull
    @Size(min=1, max=30)
    private String bookTitle;

    @Size(min=10, max=10)
    @Pattern(regexp = "^[0-9]*$")
    private String bookIban;

    @NotNull
    @Size(min=1, max=30)
    private String bookAuthor;
}
