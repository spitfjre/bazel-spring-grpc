package com.spitfjre.pet.service;

import com.spitfjre.pet.dao.PetDao;
import com.spitfjre.pet.entity.CategoryDbo;
import com.spitfjre.pet.entity.PetDbo;
import com.spitfjre.pet.entity.TagDbo;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.swagger.petstore.api.v2.AddPetRequest;
import io.swagger.petstore.api.v2.Pet;
import io.swagger.petstore.api.v2.Tag;
import io.swagger.petstore.api.v2.UpdatePetWithFormDataRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {

    private final CategoryService categoryService;
    private final PetDao petDao;
    private final TagService tagService;

    public Pet getPetById(@NonNull final Long petId) {
        return petDao
            .findById(petId)
            .map(PetDbo::convertToDto)
            .orElseThrow(() -> new StatusRuntimeException(Status.NOT_FOUND));
    }

    public List<Pet> getPetsByStatuses(@NonNull final List<Pet.Status> statuses) {
        return petDao.findAllByStatusIn(statuses).stream().map(PetDbo::convertToDto).collect(Collectors.toList());
    }

    public List<Pet> getPetsByTags(@NonNull final List<String> tagNames) {
        final List<TagDbo> tags = tagService.getAllByNames(tagNames);
        return petDao.findAllByTags(tags).stream().map(PetDbo::convertToDto).collect(Collectors.toList());
    }

    public PetDbo getPetDboById(@NonNull final Long petId) {
        return petDao.findById(petId).orElseThrow(() -> new StatusRuntimeException(Status.NOT_FOUND));
    }

    public Pet createPet(@NonNull final AddPetRequest request) {
        final CategoryDbo categoryDbo = categoryService.getCategoryDboById(request.getCategoryId());

        final PetDbo petDbo = PetDbo
            .builder()
            .category(categoryDbo)
            .name(request.getName())
            .status(request.getStatus())
            .build();

        final PetDbo savedPetDbo = petDao.save(petDbo);

        final List<TagDbo> tags = tagService.getAllByIds(request.getTagIdsList());
        savedPetDbo.setTags(tags);

        final PetDbo updatedPetDbo = petDao.save(savedPetDbo);

        return updatedPetDbo.convertToDto();
    }

    public void updatePet(@NonNull final Pet pet) {
        final PetDbo petDbo = getPetDboById(pet.getId());

        final List<TagDbo> tags = tagService.getAllByIds(
            pet.getTagsList().stream().map(Tag::getId).collect(Collectors.toList())
        );
        final CategoryDbo categoryDbo = categoryService.getCategoryDboById(pet.getCategory().getId());

        final PetDbo updatePetDbo = petDbo
            .toBuilder()
            .category(categoryDbo)
            .name(pet.getName())
            .status(pet.getStatus())
            .build();

        updatePetDbo.getTags().clear();
        updatePetDbo.getTags().addAll(tags);

        petDao.save(updatePetDbo);
    }

    public void updatePet(@NonNull final UpdatePetWithFormDataRequest request) {
        final PetDbo updatePetDbo = getPetDboById(request.getPetId())
            .toBuilder()
            .name(request.getName())
            .status(request.getStatus())
            .build();

        petDao.save(updatePetDbo);
    }

    public void deletePet(@NonNull final Long petId) {
        petDao.deleteById(petId);
    }
}
