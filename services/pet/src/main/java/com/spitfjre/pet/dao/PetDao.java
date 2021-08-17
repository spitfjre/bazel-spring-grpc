package com.spitfjre.pet.dao;

import com.spitfjre.pet.entity.PetDbo;
import com.spitfjre.pet.entity.TagDbo;
import io.swagger.petstore.api.v2.Pet;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetDao extends JpaRepository<PetDbo, Long> {
    List<PetDbo> findAllByStatusIn(@NonNull final List<Pet.Status> statuses);

    @Query("SELECT e FROM PetDbo e WHERE :size = (SELECT COUNT (tag.name) FROM e.tags tag WHERE tag IN :tags)")
    List<PetDbo> findAllByTags(@NonNull final List<TagDbo> tags);
}
