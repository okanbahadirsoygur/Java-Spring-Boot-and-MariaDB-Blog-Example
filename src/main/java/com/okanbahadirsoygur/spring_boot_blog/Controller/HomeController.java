package com.okanbahadirsoygur.spring_boot_blog.Controller;

import com.okanbahadirsoygur.spring_boot_blog.Entities.*;
import com.okanbahadirsoygur.spring_boot_blog.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    HomeRepos homerepos;

    //autowired ile pagesRepos'unu tanımlamak zorunda kalmıyoruz. Autowired bizim için otomatik tanımlama yapıyor ve ilgili entity ile bağlantısını otomatik yapıyor.
    @Autowired
    PagesRepos pagesRepos;

    //autowired ile categoriesRepos'unu tanımlamak zorunda kalmıyoruz. Autowired bizim için otomatik tanımlama yapıyor ve ilgili entity ile bağlantısını otomatik yapıyor.
    @Autowired
    CategoriesRepos categoriesRepos;

    @Autowired
    SettingsRepos settingsRepos;

    @Autowired
    SliderRepos sliderRepos;

    @GetMapping(value = "/api")
    public List<Home> api(@RequestParam(required = true, defaultValue = "", value = "id") String id) {

         //kullanılmıyor
        Home h = new Home();

        h.setIsim("deneme123");
        h.setFiyat(43.0);

        homerepos.save(h);

        List<Home> products = new ArrayList<>();
        homerepos.findAll().forEach(products::add);


        return products;

    }

    //Sayfalar arası session taşımak için HttpSession tanımlaması yapılmalıdır. Fonksiyon içinde tanımlaması yapılmaz.
    @GetMapping(value = "/")
    public ModelAndView index(@RequestParam(defaultValue = "", value = "q") String q ,HttpSession sessions) {

        sessions.setAttribute("deneme", "okan bahadir soygür");


        //java metotlarımızı html sayfalarına bağladığımız class.
        ModelAndView modelAndView = new ModelAndView();

        //html sayfasına java'daki Objeleri/ArrayListleri/Değişkenleri gönderelim.
        //Daha sonra thymeleaf kullanarak html sayfasında attributeName ile bu objelere erişeceğiz ve html sayfasında render edeceğiz.
        modelAndView.addObject("title", "Home");
        modelAndView.addObject("pages", sayfalariGetir());
        modelAndView.addObject("categories", kategorileriGetir());
        modelAndView.addObject("settings", ayarlariGetir());
        modelAndView.addObject("sonOnSayfalar", sonOnSayfayiGetir());
        modelAndView.addObject("sliders",sliderGetir());
        //modelAndView.addObject("session_okan",sessions.getAttribute("deneme"));


        //html sayfamızın adı.
        modelAndView.setViewName("index");

        //geriye View'i(html sayfasını) döndür.
        return modelAndView;

    }




    @GetMapping(value = "/categories/{slug}")
    public ModelAndView kategoriler(@PathVariable String slug, HttpSession sessions){



        //gelen kategorinin slug'ından bütün bilgilerini çekelim.
        Categories categorie = categoriesRepos.findBySlug(slug);

        List<Pages> pagesList = new ArrayList<>();

        //gelen kategorinin id bilgisini pagesRepos'a verelim. Bize o categoriy id sine ait bütün pages'ları döndürsün.
        pagesRepos.findByCategorie_id(categorie.getId()).forEach(pagesList::add);





        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("slug",slug);
        modelAndView.addObject("categories", kategorileriGetir());
        modelAndView.addObject("title", categorie.getTitle());
        modelAndView.addObject("settings", ayarlariGetir());
        modelAndView.addObject("categorie",categorie);
        modelAndView.addObject("sayfalar",pagesList);




        modelAndView.setViewName("kategoriler");



        return modelAndView;
    }


    @GetMapping(value = "/pages/{slug}")
    public ModelAndView sayfalar(@PathVariable String slug,HttpSession sessions){


        Pages pages = new Pages();
        pages =  pagesRepos.findBySlug(slug);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", kategorileriGetir());
        modelAndView.addObject("title", pages.getTitle());
        modelAndView.addObject("settings", ayarlariGetir());
        modelAndView.addObject("sayfa", pages);


        modelAndView.setViewName("sayfalar");

        return modelAndView;


    }






    //veritabanındaki sayfaları List türünde geriye döndürür.
    public List<Pages> sayfalariGetir(){

        List<Pages> pagesList = new ArrayList<>();

        //Crud repos yardımıyla pages tablomuzdaki bütün dataları pagesList adlı ArrayList'e ekleyelim.
        pagesRepos.findAll().forEach(pagesList::add);

        return pagesList;

    }


    public List<Pages> sonOnSayfayiGetir(){

        List<Pages> pagesList = new ArrayList<>();

        pagesRepos.findAllLimit10().forEach(pagesList::add);

        return pagesList;

    }


    //veritabanındaki kategorileri List türünde geri döndürür.
    public List<Categories> kategorileriGetir(){


        List<Categories> categoriesList = new ArrayList<>();

       //Crud repos yardımıyla categories tablomuzdaki bütün dataları categoriesRepos adlı ArrayList'e ekleyelim.
        categoriesRepos.getCategoriesOrderBySira().forEach(categoriesList::add);

        return categoriesList;

    }



    public List<Settings> ayarlariGetir(){

       List<Settings> settingsList = new ArrayList<>();

        settingsRepos.findAll().forEach(settingsList::add);

        return settingsList;

    }

    public List<Slider> sliderGetir(){

        List<Slider> sliderList = new ArrayList<>();

        //rank'a göre küçükten büyüge sıralı getirelim
        sliderRepos.getSlidersOrderByRank().forEach(sliderList::add);

        return sliderList;


    }




}




