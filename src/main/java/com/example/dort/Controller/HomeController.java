package com.example.dort.Controller;

import com.example.dort.Entities.Home;
import com.example.dort.repos.HomeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class HomeController {

    @Autowired
    HomeRepos homerepos;

    @GetMapping(value = "/api")
    public List<Home> api(@RequestParam(required = true,defaultValue = "",value = "id") String id){

        Home h  = new Home();

        h.setIsim("deneme123");
        h.setFiyat(43.0);

        homerepos.save(h);

        List<Home> products = new ArrayList<>();
        homerepos.findAll().forEach(products::add);





        return products;

    }


    @GetMapping(value = "/server")
    public String server(){

        String [] command = {"ls", "-l"};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(System.getProperty("user.home")));
        String line = "-";
        try {
            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader (new InputStreamReader(process.getInputStream()));



            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }

            int exitCode = process.waitFor();

            System.out.println ("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return line;
    }


    @GetMapping(value = "/")
    public ModelAndView index(@RequestParam(defaultValue = "", value = "q") String q){






        ModelAndView modelAndView = new ModelAndView();

        String a = "okan";
        modelAndView.addObject("user", a);
        modelAndView.setViewName("index");
        return modelAndView;

    }
}
