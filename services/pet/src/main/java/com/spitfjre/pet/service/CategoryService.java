package com.spitfjre.pet.service;

import com.spitfjre.pet.dao.CategoryDao;
import com.spitfjre.pet.entity.CategoryDbo;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.swagger.petstore.api.v2.Category;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryDao categoryDao;

    public Category getCategoryById(@NonNull final Long categoryId) {
        return categoryDao
            .findById(categoryId)
            .map(CategoryDbo::convertToDto)
            .orElseThrow(() -> new StatusRuntimeException(Status.NOT_FOUND));
    }

    public CategoryDbo getCategoryDboById(@NonNull final Long categoryId) {
        return categoryDao.findById(categoryId).orElseThrow(() -> new StatusRuntimeException(Status.NOT_FOUND));
    }
}
