package com.spitfjre.pet.dao;

import com.spitfjre.pet.entity.TagDbo;
import java.util.List;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<TagDbo, Long> {
    List<TagDbo> findAllByNameIn(@NonNull final List<String> tagNames);
}
