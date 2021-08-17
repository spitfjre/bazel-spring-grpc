package com.spitfjre.pet.entity;

import io.swagger.petstore.api.v2.Category;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Builder
@Data
@Entity
@NoArgsConstructor
@Table(name = "category")
public class CategoryDbo implements DtoConvertable<Category> {

    @Column(name = "id")
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "name")
    @Length(max = 50)
    @NotBlank
    private String name;

    @Override
    public Category convertToDto() {
        return Category.newBuilder().setId(id).setName(name).build();
    }
}
