package com.spitfjre.store.entity;

import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import io.swagger.petstore.api.v2.Order;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "order_table")
public class OrderDbo implements DtoConvertable<Order> {

    @Column(name = "id")
    @GeneratedValue
    @Id
    private Long id;

    @Column(name = "pet_id")
    @NotNull
    private Long petId;

    @Column(name = "quantity")
    @NotNull
    @Min(1)
    private Integer quantity;

    @Column(name = "ship_date")
    private LocalDateTime shipDate;

    @Column(name = "status")
    @NotNull
    private Order.Status status;

    @Column(name = "complete")
    @NotNull
    private Boolean complete;

    @Override
    public Order convertToDto() {
        return Order
            .newBuilder()
            .setComplete(complete)
            .setId(id)
            .setPetId(petId)
            .setQuantity(quantity)
            .setShipDate(
                Optional
                    .ofNullable(shipDate)
                    .map(s -> s.toEpochSecond(ZoneOffset.UTC))
                    .map(Timestamps::fromSeconds)
                    .orElse(Timestamp.getDefaultInstance())
            )
            .setStatus(status)
            .build();
    }
}
