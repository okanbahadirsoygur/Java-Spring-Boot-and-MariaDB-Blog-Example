package com.example.dort.Controller;

import com.example.dort.Entities.Settings;
import com.example.dort.repos.SettingsRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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


    /**
     * <p>
     * Bu sayfaya sadece post metodu ile erişilebilir.
     * /admin/settings sayfasından güncellenip gönderilen veriler bu sayfaya post ile gelir.
     * </p>
     *
     * <p>
     * RequestParam annotation'ı ile gelen json verilerini, tanımladığımız değişken isimleri ile eşleyip yakalayabiliyoruz.
     * </p>
     */
    @PostMapping("/admin/settings/update")
    public RedirectView settings_update(@RequestParam String LOGO_URL, String FOOTER_DESC, String FOOTER_LICANCE, String LOGO_TEXT, String GITHUB_LINK){


        settingsRepos.setSettingsByKey("LOGO_URL",LOGO_URL);
        settingsRepos.setSettingsByKey("LOGO_TEXT",LOGO_TEXT);
        settingsRepos.setSettingsByKey("FOOTER_DESC",FOOTER_DESC);
        settingsRepos.setSettingsByKey("FOOTER_LICANCE",FOOTER_LICANCE);
        settingsRepos.setSettingsByKey("GITHUB_LINK",GITHUB_LINK);

        //önceki sayfaya yeniden yönlendirelim.
        return new RedirectView("/admin/settings");



    }



    public List<Settings> ayarlariGetir(){

        List<Settings> settingsList = new ArrayList<>();

        settingsRepos.findAll().forEach(settingsList::add);

        return settingsList;

    }

}
