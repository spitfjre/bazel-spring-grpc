package com.spitfjre.store.controller;

import com.google.protobuf.Empty;
import com.spitfjre.store.service.InventoryService;
import com.spitfjre.store.service.OrderService;
import io.grpc.stub.StreamObserver;
import io.swagger.petstore.api.v2.CreateOrderRequest;
import io.swagger.petstore.api.v2.CreateOrderResponse;
import io.swagger.petstore.api.v2.DeleteOrderRequest;
import io.swagger.petstore.api.v2.GetInventoryResponse;
import io.swagger.petstore.api.v2.GetOrderRequest;
import io.swagger.petstore.api.v2.GetOrderResponse;
import io.swagger.petstore.api.v2.Order;
import io.swagger.petstore.api.v2.StoreServiceGrpc;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class StoreController extends StoreServiceGrpc.StoreServiceImplBase {

    private final InventoryService inventoryService;
    private final OrderService orderService;

    @Override
    public void getInventory(final Empty request, final StreamObserver<GetInventoryResponse> responseObserver) {
        final Map<String, Integer> inventory = inventoryService.getInventoryByStatus();
        final GetInventoryResponse response = GetInventoryResponse
            .newBuilder()
            .putAllInventoryByStatus(inventory)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createOrder(
        final CreateOrderRequest request,
        final StreamObserver<CreateOrderResponse> responseObserver
    ) {
        final Order order = orderService.createOrder(request);
        final CreateOrderResponse response = CreateOrderResponse.newBuilder().setOrder(order).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getOrder(final GetOrderRequest request, final StreamObserver<GetOrderResponse> responseObserver) {
        final Order order = orderService.getOrderById(request.getOrderId());
        final GetOrderResponse response = GetOrderResponse.newBuilder().setOrder(order).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteOrder(final DeleteOrderRequest request, final StreamObserver<Empty> responseObserver) {
        orderService.deleteOrder(request.getOrderId());
        final Empty response = Empty.newBuilder().build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
