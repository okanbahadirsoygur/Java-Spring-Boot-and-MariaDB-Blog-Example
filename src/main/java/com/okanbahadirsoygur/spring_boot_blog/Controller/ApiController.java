package com.okanbahadirsoygur.spring_boot_blog.Controller;

import com.okanbahadirsoygur.spring_boot_blog.Entities.Pages;
import com.okanbahadirsoygur.spring_boot_blog.repos.PagesRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {


    /**
     *  Header'daki arama alanı pure javascript ile buraya bağlanır(fetch), Pagesrepos ile gelen değer sayfalar içerisinde Like ile aranır.
     *  Daha sonra gelen sonuçları ekrana yazar.
     */


    @Autowired
    PagesRepos pagesRepos;

    @GetMapping("/search/{query}")
    //@PathVariable tanımlaması yapılmaz ise gelen datayı fonksiyon tutamayacaktır.
    public List<Pages> arama(@PathVariable String query){


        List<Pages> pagesList = new ArrayList<>();

        pagesRepos.searchByLike(query).forEach(pagesList::add);

        return pagesList;

    }
}
