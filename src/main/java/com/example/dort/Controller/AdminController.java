package com.example.dort.Controller;

import com.example.dort.Entities.Categories;
import com.example.dort.Entities.Settings;
import com.example.dort.Entities.Slider;
import com.example.dort.repos.CategoriesRepos;
import com.example.dort.repos.SettingsRepos;
import com.example.dort.repos.SliderRepos;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    SettingsRepos settingsRepos;

    @Autowired
    SliderRepos sliderRepos;

    @Autowired
    CategoriesRepos categoriesRepos;


    /**
     * Yönetim Paneli ile ilgili işlemler bu Controller tarafından yapılır.
     */

    @GetMapping("/admin")
    public ModelAndView admin(HttpSession sessions){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("admin/index");

        return modelAndView;

    }




    @GetMapping("/admin/settings")
    public ModelAndView settings(HttpSession sessions){

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
    public ModelAndView sliders(HttpSession sessions){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("slug_sliders", true);
        modelAndView.addObject("sliders", sliderlariGetir());

        modelAndView.setViewName("/admin/sliders");

        return modelAndView;

    }


    @GetMapping("/admin/categories")
    public ModelAndView categories(HttpSession sessions){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("slug_categories",true);
        modelAndView.addObject("categories", kategoriGetir());
        modelAndView.setViewName("/admin/categories");

        return modelAndView;

    }


    /**
     * <p>Javascript(fetch) ile id değerini json formatında buraya yolluyorum.</p>
     * <p>RequestBody ile bu data'yı alıp, Slider objesi(entities,class) ile eşliyoruz.
     * Json formatındaki data'ları değişkenlerde tutamıyorum, belki bir yolu vardır ama ben bulamadım. Objeler üzerinden json datalarını eşleyip, erişimi bu şekilde gerçekleştiriyoruz.</p>
     */

    @PostMapping(value = "/admin/sliders/delete")
    public String sliders_delete(@RequestBody Slider slider, HttpSession sessions){

      sliderRepos.deleteById(Long.valueOf(slider.getId()));

        return "deleted";
       // return  new RedirectView("/admin/settings");


    }


    @PostMapping(value = "/admin/sliders/update")
    public int sliders_update(@RequestBody Slider slider, HttpSession sessions){

        sliderRepos.updateSliderById(slider.getId(), slider.getTitle(), slider.getImg_url(), slider.getRank(),slider.getUrl());


        return 1;


    }



    @PostMapping(value = "/admin/sliders/add")
    public int sliders_add(@RequestBody Slider slider, HttpSession sessions){

        //slider objesi yaratalım. Daha sonra bize json ile gönderilen slider objesindeki değerleri burdaki objeye yollayalım.
        Slider sliderEntities = new Slider();

        sliderEntities.setTitle(slider.getTitle());
        sliderEntities.setImg_url(slider.getImg_url());
        sliderEntities.setUrl(slider.getUrl());

        //repostry'e sliderEntities'i yollayalım veritabanına eklesin.
        sliderRepos.save(sliderEntities);

        return 1;


    }




    /**
     * <p>Javascript(fetch) ile id değerini json formatında buraya yolluyorum.</p>
     * <p>RequestBody ile bu data'yı alıp, Slider objesi(entities,class) ile eşliyoruz.
     * Json formatındaki data'ları değişkenlerde tutamıyorum, belki bir yolu vardır ama ben bulamadım. Objeler üzerinden json datalarını eşleyip, erişimi bu şekilde gerçekleştiriyoruz.</p>
     */

    @PostMapping(value = "/admin/categories/delete")
    public String categories_delete(@RequestBody Categories categorie, HttpSession sessions){

        categoriesRepos.deleteById(Long.valueOf(categorie.getId()));

        return "deleted";
        // return  new RedirectView("/admin/settings");


    }


    @PostMapping(value = "/admin/categories/add")
    public int categories_add(@RequestBody Categories categorie, HttpSession sessions){

        //slider objesi yaratalım. Daha sonra bize json ile gönderilen slider objesindeki değerleri burdaki objeye yollayalım.
        Categories categoriesEntities = new Categories();

        categoriesEntities.setTitle(categorie.getTitle());
        categoriesEntities.setDescription(categorie.getDescription());
        categoriesEntities.setSlug(categorie.getSlug());
        categoriesEntities.setRank(categorie.getRank());

        //repostry'e sliderEntities'i yollayalım veritabanına eklesin.
        categoriesRepos.save(categoriesEntities);

        return 1;


    }


    @PostMapping(value = "/admin/categories/update")
    public int sliders_update(@RequestBody Categories categorie, HttpSession sessions){

        categoriesRepos.updateCategorieById(categorie.getId(), categorie.getTitle(), categorie.getSlug(),categorie.getDescription(), categorie.getRank());


        return 1;


    }


    public List<Settings> ayarlariGetir(){

        List<Settings> settingsList = new ArrayList<>();

        settingsRepos.findAll().forEach(settingsList::add);

        return settingsList;

    }

    public List<Slider> sliderlariGetir(){

        List<Slider> sliderList = new ArrayList<>();

        sliderRepos.getSlidersOrderByRank().forEach(sliderList::add);

        return sliderList;

    }


    public List<Categories> kategoriGetir(){

        List<Categories> categoriesList = new ArrayList<>();

        categoriesRepos.getCategoriesOrderBySira().forEach(categoriesList::add);

        return categoriesList;


    }

}
