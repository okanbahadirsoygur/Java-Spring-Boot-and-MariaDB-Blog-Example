package com.example.dort.Controller;

import com.example.dort.Entities.Settings;
import com.example.dort.repos.SettingsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    SettingsRepos settingsRepos;


    /**
     * Yönetim Paneli ile ilgili işlemler bu Controller tarafından yapılır.
     */

    @GetMapping("/admin")
    public ModelAndView admin(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("admin/index");

        return modelAndView;

    }




    @GetMapping("/admin/settings")
    public ModelAndView settings(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("slug_settings",true);
        modelAndView.addObject("settings",ayarlariGetir());

        modelAndView.setViewName("admin/settings");

        return modelAndView;

    }



    public List<Settings> ayarlariGetir(){

        List<Settings> settingsList = new ArrayList<>();

        settingsRepos.findAll().forEach(settingsList::add);

        return settingsList;

    }

}
