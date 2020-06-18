package com.example.Tim25Xml.service;

import com.example.Tim25Xml.model.ImageModel;
import org.springframework.stereotype.Service;

import java.util.Collection;


public interface ImageModelService {
    public ImageModel findByCarId(String id);
    public Collection<ImageModel> findImages(String id);

}
