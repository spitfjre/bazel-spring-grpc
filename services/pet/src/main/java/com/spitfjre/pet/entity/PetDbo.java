package com.spitfjre.pet.entity;

import io.swagger.petstore.api.v2.Pet;
import io.swagger.petstore.api.v2.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "pet")
public class PetDbo implements DtoConvertable<Pet> {

    @Column(name = "id")
    @GeneratedValue
    @Id
    private Long id;

    @JoinColumn(name = "category_id", unique = true, nullable = false, updatable = false)
    @ManyToOne
    private CategoryDbo category;

    @Column(name = "name")
    @Length(max = 50)
    @NotBlank
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private List<ImageDbo> images;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "pet_tag",
        joinColumns = { @JoinColumn(name = "pet_id") },
        inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private List<TagDbo> tags;

    @Column(name = "status")
    @NotNull
    private Pet.Status status;

    @Override
    public Pet convertToDto() {
        final List<String> photoUrls = Optional
            .ofNullable(images)
            .map(i -> i.stream().map(ImageDbo::getUrl).collect(Collectors.toList()))
            .orElse(new ArrayList<>());
        final List<Tag> convertedTags = Optional
            .ofNullable(tags)
            .map(t -> t.stream().map(TagDbo::convertToDto).collect(Collectors.toList()))
            .orElse(new ArrayList<>());

        return Pet
            .newBuilder()
            .addAllPhotoUrls(photoUrls)
            .addAllTags(convertedTags)
            .setCategory(category.convertToDto())
            .setId(id)
            .setName(name)
            .setStatus(status)
            .build();
    }
}
