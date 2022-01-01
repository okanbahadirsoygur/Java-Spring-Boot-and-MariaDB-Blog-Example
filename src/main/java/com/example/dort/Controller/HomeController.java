package com.example.dort.Controller;

import com.example.dort.Entities.Home;
import com.example.dort.repos.HomeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    HomeRepos homerepos;

    @GetMapping(value = "/")
    public List<Home> api(@RequestParam(required = true,defaultValue = "",value = "id") String id){


        List<Home> products = new ArrayList<>();
        homerepos.findAll().forEach(products::add);


        return products;

    }
}
