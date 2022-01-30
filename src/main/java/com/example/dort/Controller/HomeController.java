package com.example.dort.Controller;

import com.example.dort.Entities.Categories;
import com.example.dort.Entities.Home;
import com.example.dort.Entities.Pages;
import com.example.dort.repos.CategoriesRepos;
import com.example.dort.repos.HomeRepos;
import com.example.dort.repos.PagesRepos;
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

    //autowired ile pagesRepos'unu tanımlamak zorunda kalmıyoruz. Autowired bizim için otomatik tanımlama yapıyor ve ilgili entity ile bağlantısını otomatik yapıyor.
    @Autowired
    PagesRepos pagesRepos;

    //autowired ile categoriesRepos'unu tanımlamak zorunda kalmıyoruz. Autowired bizim için otomatik tanımlama yapıyor ve ilgili entity ile bağlantısını otomatik yapıyor.
    @Autowired
    CategoriesRepos categoriesRepos;

    @GetMapping(value = "/api")
    public List<Home> api(@RequestParam(required = true, defaultValue = "", value = "id") String id) {

        Home h = new Home();

        h.setIsim("deneme123");
        h.setFiyat(43.0);

        homerepos.save(h);

        List<Home> products = new ArrayList<>();
        homerepos.findAll().forEach(products::add);


        return products;

    }


    @GetMapping(value = "/server")
    public String server() {

        String[] command = {"ls", "-l"};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(System.getProperty("user.home")));
        String line = "-";
        try {
            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));


            while ((line = reader.readLine()) != null) {
                System.out.println(line);

            }

            int exitCode = process.waitFor();

            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return line;
    }


    @GetMapping(value = "/")
    public ModelAndView index(@RequestParam(defaultValue = "", value = "q") String q) {


        //java metotlarımızı html sayfalarına bağladığımız class.
        ModelAndView modelAndView = new ModelAndView();

        //html sayfasına java'daki Objeleri/ArrayListleri/Değişkenleri gönderelim.
        //Daha sonra thymeleaf kullanarak html sayfasında attributeName ile bu objelere erişeceğiz ve html sayfasında render edeceğiz.
        modelAndView.addObject("title", "Ana Sayfa");
        modelAndView.addObject("pages", sayfalariGetir());
        modelAndView.addObject("categories", kategorileriGetir());

        //html sayfamızın adı.
        modelAndView.setViewName("index");

        //geriye View'i(html sayfasını) döndür.
        return modelAndView;

    }



    
    @GetMapping(value = "/kategoriler/{slug}")
    public ModelAndView kategoriler(@PathVariable String slug){


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("slug",slug);
        modelAndView.addObject("categories", kategorileriGetir());

        modelAndView.setViewName("kategoriler");



        return modelAndView;
    }






    //veritabanındaki sayfaları List türünde geriye döndürür.
    public List<Pages> sayfalariGetir(){

        List<Pages> pagesList = new ArrayList<>();

        //Crud repos yardımıyla pages tablomuzdaki bütün dataları pagesList adlı ArrayList'e ekleyelim.
        pagesRepos.findAll().forEach(pagesList::add);

        return pagesList;

    }


    //veritabanındaki kategorileri List türünde geri döndürür.
    public List<Categories> kategorileriGetir(){


        List<Categories> categoriesList = new ArrayList<>();

       //Crud repos yardımıyla categories tablomuzdaki bütün dataları categoriesRepos adlı ArrayList'e ekleyelim.
        categoriesRepos.findAll().forEach(categoriesList::add);

        return categoriesList;


    }

}




