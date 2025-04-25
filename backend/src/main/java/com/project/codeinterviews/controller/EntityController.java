package com.project.codeinterviews.controller;

import com.project.codeinterviews.DTO.EntityMessageDTO;
import com.project.codeinterviews.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/helloworld")
public class EntityController {
    @Autowired
    private EntityService entityService;

    @GetMapping
    public EntityMessageDTO find() {
        return entityService.printMessage();
    }
}
