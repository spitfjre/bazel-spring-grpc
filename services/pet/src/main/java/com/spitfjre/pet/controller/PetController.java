package com.spitfjre.pet.controller;

import com.google.protobuf.Empty;
import com.spitfjre.pet.service.PetService;
import io.grpc.stub.StreamObserver;
import io.swagger.petstore.api.v2.AddPetRequest;
import io.swagger.petstore.api.v2.AddPetResponse;
import io.swagger.petstore.api.v2.DeletePetRequest;
import io.swagger.petstore.api.v2.FindPetRequest;
import io.swagger.petstore.api.v2.FindPetResponse;
import io.swagger.petstore.api.v2.FindPetsByStatusRequest;
import io.swagger.petstore.api.v2.FindPetsByStatusResponse;
import io.swagger.petstore.api.v2.FindPetsByTagsRequest;
import io.swagger.petstore.api.v2.FindPetsByTagsResponse;
import io.swagger.petstore.api.v2.Pet;
import io.swagger.petstore.api.v2.PetServiceGrpc;
import io.swagger.petstore.api.v2.UpdatePetRequest;
import io.swagger.petstore.api.v2.UpdatePetWithFormDataRequest;
import io.swagger.petstore.api.v2.UploadImageRequest;
import io.swagger.petstore.api.v2.UploadImageResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class PetController extends PetServiceGrpc.PetServiceImplBase {

    private final PetService petService;

    @Override
    public void uploadImage(
        final UploadImageRequest request,
        final StreamObserver<UploadImageResponse> responseObserver
    ) {
        responseObserver.onNext(null);
        responseObserver.onCompleted();
    }

    @Override
    public void addPet(final AddPetRequest request, final StreamObserver<AddPetResponse> responseObserver) {
        final Pet pet = petService.createPet(request);
        final AddPetResponse response = AddPetResponse.newBuilder().setPet(pet).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updatePet(final UpdatePetRequest request, final StreamObserver<Empty> responseObserver) {
        petService.updatePet(request.getPet());
        final Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findPetsByStatus(
        final FindPetsByStatusRequest request,
        final StreamObserver<FindPetsByStatusResponse> responseObserver
    ) {
        final List<Pet> pets = petService.getPetsByStatuses(request.getStatusesList());
        final FindPetsByStatusResponse response = FindPetsByStatusResponse.newBuilder().addAllPets(pets).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findPetsByTags(
        final FindPetsByTagsRequest request,
        final StreamObserver<FindPetsByTagsResponse> responseObserver
    ) {
        final List<Pet> pets = petService.getPetsByTags(request.getTagsList());
        final FindPetsByTagsResponse response = FindPetsByTagsResponse.newBuilder().addAllPets(pets).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findPet(final FindPetRequest request, final StreamObserver<FindPetResponse> responseObserver) {
        final Pet pet = petService.getPetById(request.getPetId());
        final FindPetResponse response = FindPetResponse.newBuilder().setPet(pet).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updatePetWithFormData(
        final UpdatePetWithFormDataRequest request,
        final StreamObserver<Empty> responseObserver
    ) {
        petService.updatePet(request);
        final Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deletePet(final DeletePetRequest request, final StreamObserver<Empty> responseObserver) {
        petService.deletePet(request.getPetId());
        final Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
