package com.example.demo.dto;

import com.example.demo.domain.Shop;
import com.example.demo.validators.RequiredIf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredIf(message = "Invalid Shop field")
public class OwnerDto {
    @NotNull
    @Size(min = 13, max = 13)
    private String cnp;
    private Boolean hasShop;
    private String nume;
    private Shop shop;
}
