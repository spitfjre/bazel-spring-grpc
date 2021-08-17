package com.spitfjre.pet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(name = "image")
public class ImageDbo {

    @Column(name = "id")
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "url")
    @NotBlank
    @Length(max = 250)
    private String url;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private PetDbo pet;
}
