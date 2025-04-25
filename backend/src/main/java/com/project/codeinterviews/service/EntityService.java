package com.project.codeinterviews.service;

import com.project.codeinterviews.entity.EntityMessage;
import com.project.codeinterviews.DTO.EntityMessageDTO;

import org.springframework.stereotype.Service;

@Service
public class EntityService {

    public EntityMessageDTO printMessage() {
        EntityMessage entityMessage = new EntityMessage();
        entityMessage.setMessage("hello world!");
        return new EntityMessageDTO(entityMessage.getMessage());
    }
}
