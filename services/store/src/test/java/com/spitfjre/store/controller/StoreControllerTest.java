package com.spitfjre.store.controller;

import com.google.protobuf.Timestamp;
import com.spitfjre.store.client.PetClient;
import io.swagger.petstore.api.v2.CreateOrderRequest;
import io.swagger.petstore.api.v2.CreateOrderResponse;
import io.swagger.petstore.api.v2.Order;
import io.swagger.petstore.api.v2.Pet;
import io.swagger.petstore.api.v2.StoreServiceGrpc.StoreServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@DirtiesContext
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StoreControllerTest {

    @MockBean
    private PetClient petClient;

    @GrpcClient("inProcess")
    @SuppressWarnings("unused")
    private StoreServiceBlockingStub blockingStub;

    @Test
    public void givenValidPetId_createOrder_isSuccessful() {
        // given
        final long petId = 1L;
        final int quantity = 2;

        final CreateOrderRequest request = CreateOrderRequest
            .newBuilder()
            .setPetId(petId)
            .setQuantity(quantity)
            .build();

        // when
        Mockito.when(petClient.findPet(petId)).thenReturn(Pet.newBuilder().build());

        final CreateOrderResponse response = blockingStub.createOrder(request);

        // then
        final CreateOrderResponse expected = CreateOrderResponse
            .newBuilder()
            .setOrder(
                Order
                    .newBuilder()
                    .setComplete(false)
                    .setId(response.getOrder().getId())
                    .setPetId(petId)
                    .setQuantity(quantity)
                    .setShipDate(Timestamp.getDefaultInstance())
                    .setStatus(Order.Status.STATUS_PLACED)
                    .build()
            )
            .build();
        Assertions.assertEquals(response, expected);
    }
}
