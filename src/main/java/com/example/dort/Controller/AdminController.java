package com.example.dort.Controller;

import com.example.dort.Entities.Settings;
import com.example.dort.Entities.Slider;
import com.example.dort.repos.SettingsRepos;
import com.example.dort.repos.SliderRepos;
import org.aspectj.apache.bcel.generic.RET;
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

    @Autowired
    SliderRepos sliderRepos;


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
     * RequestParam annotation'ı ile gelen form verilerini, tanımladığımız değişken isimleri ile eşleyip yakalayabiliyoruz.
     * </p>
     *
     * <p>
     * RedirectView nesnesi ile işlemler bittikten sonra kullanıcıyı geldiği sayfaya yollayalım.
     * </p>
     *
     * <p>RequestParam form datası post edildiğinde kullanılıyor. Javascript ile json datası yollanmak istenir ise o zaman RequestBody kullanmamız gerekiyor, ve gelen json datasını class ile eşlemek gerekiyor. Değişkende tutulamıyor. Bir class(Entities) bağlamamız gerekiyor</p>
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




    @GetMapping("/admin/sliders")
    public ModelAndView sliders(){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("slug_sliders", true);
        modelAndView.addObject("sliders", sliderlariGetir());

        modelAndView.setViewName("/admin/sliders");

        return modelAndView;

    }

    /**
     * <p>Javascript(fetch) ile id değerini json formatında buraya yolluyorum.</p>
     * <p>RequestBody ile bu data'yı alıp, Slider objesi(entities,class) ile eşliyoruz.
     * Json formatındaki data'ları değişkenlerde tutamıyorum, belki bir yolu vardır ama ben bulamadım. Objeler üzerinden json datalarını eşleyip, erişimi bu şekilde gerçekleştiriyoruz.</p>
     */

    @PostMapping(value = "/admin/sliders/delete")
    public String sliders_delete(@RequestBody Slider slider){

      sliderRepos.deleteById(Long.valueOf(slider.getId()));

        return "deleted";
       // return  new RedirectView("/admin/settings");


    }


    @PostMapping(value = "/admin/sliders/update")
    public int sliders_update(@RequestBody Slider slider){

        sliderRepos.updateSliderById(slider.getId(), slider.getTitle(), slider.getImg_url(), slider.getRank(),slider.getUrl());


        return 1;


    }



    @PostMapping(value = "/admin/sliders/add")
    public int sliders_add(@RequestBody Slider slider){

        //slider objesi yaratalım. Daha sonra bize json ile gönderilen slider objesindeki değerleri burdaki objeye yollayalım.
        Slider sliderEntities = new Slider();

        sliderEntities.setTitle(slider.getTitle());
        sliderEntities.setImg_url(slider.getImg_url());
        sliderEntities.setUrl(slider.getUrl());

        //repostry'e sliderEntities'i yollayalım veritabanına eklesin.
        sliderRepos.save(sliderEntities);

        return 1;


    }


    public List<Settings> ayarlariGetir(){

        List<Settings> settingsList = new ArrayList<>();

        settingsRepos.findAll().forEach(settingsList::add);

        return settingsList;

    }

    public List<Slider> sliderlariGetir(){

        List<Slider> sliderList = new ArrayList<>();

        sliderRepos.findAll().forEach(sliderList::add);

        return sliderList;

    }

}
