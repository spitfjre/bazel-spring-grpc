package com.spitfjre.pet.controller;

import com.spitfjre.pet.dao.CategoryDao;
import com.spitfjre.pet.dao.TagDao;
import com.spitfjre.pet.entity.CategoryDbo;
import com.spitfjre.pet.entity.TagDbo;
import io.swagger.petstore.api.v2.AddPetRequest;
import io.swagger.petstore.api.v2.AddPetResponse;
import io.swagger.petstore.api.v2.Pet;
import io.swagger.petstore.api.v2.PetServiceGrpc.PetServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PetControllerTest {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TagDao tagDao;

    @GrpcClient("inProcess")
    @SuppressWarnings("unused")
    private PetServiceBlockingStub blockingStub;

    @Test
    public void givenAddPetRequest_addPet_isSuccessful() {
        // given
        final String petName = "#PET_NAME";
        final Pet.Status petStatus = Pet.Status.STATUS_AVAILABLE;

        final CategoryDbo categoryDbo = categoryDao.save(CategoryDbo.builder().name("#CATEGORY_NAME").build());
        final TagDbo tagDbo = tagDao.save(TagDbo.builder().name("#TAG_NAME").build());

        final AddPetRequest request = AddPetRequest
            .newBuilder()
            .addTagIds(tagDbo.getId())
            .setCategoryId(categoryDbo.getId())
            .setName(petName)
            .setStatus(petStatus)
            .build();

        // when
        final AddPetResponse response = blockingStub.addPet(request);

        // then
        final AddPetResponse expected = AddPetResponse
            .newBuilder()
            .setPet(
                Pet
                    .newBuilder()
                    .addTags(tagDbo.convertToDto())
                    .setCategory(categoryDbo.convertToDto())
                    .setId(response.getPet().getId())
                    .setName(petName)
                    .setStatus(petStatus)
                    .build()
            )
            .build();
        Assertions.assertEquals(response, expected);
    }
}
