package ru.praktikum.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class IngredientListDto {

    private String[] ingredients;

}
