package com.spitfjre.store.service;

import com.spitfjre.store.client.PetClient;
import io.swagger.petstore.api.v2.Pet;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InventoryService {

    private final PetClient petClient;

    public Map<String, Integer> getInventoryByStatus() {
        final Map<String, Integer> map = new HashMap<>();

        map.put(Pet.Status.STATUS_AVAILABLE.name(), petClient.findPetsByStatus(Pet.Status.STATUS_AVAILABLE).size());
        map.put(Pet.Status.STATUS_PENDING.name(), petClient.findPetsByStatus(Pet.Status.STATUS_PENDING).size());
        map.put(Pet.Status.STATUS_SOLD.name(), petClient.findPetsByStatus(Pet.Status.STATUS_SOLD).size());

        return map;
    }
}
