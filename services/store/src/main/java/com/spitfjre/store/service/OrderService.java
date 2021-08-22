package com.spitfjre.store.service;

import com.spitfjre.store.client.PetClient;
import com.spitfjre.store.dao.OrderDao;
import com.spitfjre.store.entity.OrderDbo;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.swagger.petstore.api.v2.CreateOrderRequest;
import io.swagger.petstore.api.v2.Order;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderDao orderDao;
    private final PetClient petClient;

    public Order createOrder(@NonNull final CreateOrderRequest request) {
        petClient.findPet(request.getPetId());

        final OrderDbo orderDbo = OrderDbo
            .builder()
            .complete(false)
            .petId(request.getPetId())
            .quantity(request.getQuantity())
            .status(Order.Status.STATUS_PLACED)
            .build();

        return orderDao.save(orderDbo).convertToDto();
    }

    public Order getOrderById(@NonNull final Long orderId) {
        return orderDao
            .findById(orderId)
            .map(OrderDbo::convertToDto)
            .orElseThrow(() -> new StatusRuntimeException(Status.NOT_FOUND));
    }

    public void deleteOrder(@NonNull final Long orderId) {
        orderDao.deleteById(orderId);
    }
}
