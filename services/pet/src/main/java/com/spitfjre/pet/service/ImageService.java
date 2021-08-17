package com.spitfjre.pet.service;

import com.spitfjre.pet.dao.ImageDao;
import com.spitfjre.pet.dao.PetDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageDao imageDao;
    private final PetDao petDao;
}
