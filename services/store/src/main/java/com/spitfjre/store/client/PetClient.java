package com.spitfjre.store.client;

import io.swagger.petstore.api.v2.FindPetRequest;
import io.swagger.petstore.api.v2.FindPetsByStatusRequest;
import io.swagger.petstore.api.v2.Pet;
import io.swagger.petstore.api.v2.PetServiceGrpc;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetClient {

    @GrpcClient("pet")
    @SuppressWarnings("unused")
    private PetServiceGrpc.PetServiceBlockingStub blockingStub;

    public Pet findPet(@NonNull final Long petId) {
        final FindPetRequest request = FindPetRequest.newBuilder().setPetId(petId).build();
        return blockingStub.findPet(request).getPet();
    }

    public List<Pet> findPetsByStatus(@NonNull final Pet.Status status) {
        final FindPetsByStatusRequest request = FindPetsByStatusRequest.newBuilder().addStatuses(status).build();
        return blockingStub.findPetsByStatus(request).getPetsList();
    }
}
