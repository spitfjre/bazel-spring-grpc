package com.spitfjre.pet.service;

import com.spitfjre.pet.dao.TagDao;
import com.spitfjre.pet.entity.TagDbo;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagDao tagDao;

    public List<TagDbo> getAllByNames(@NonNull final List<String> tagNames) {
        return tagDao.findAllByNameIn(tagNames);
    }

    public List<TagDbo> getAllByIds(@NonNull final List<Long> tagIds) {
        return tagDao.findAllById(tagIds);
    }
}
