package com.okanbahadirsoygur.spring_boot_blog.Controller;

import com.okanbahadirsoygur.spring_boot_blog.Entities.Admin;
import com.okanbahadirsoygur.spring_boot_blog.Entities.Categories;
import com.okanbahadirsoygur.spring_boot_blog.Entities.Settings;
import com.okanbahadirsoygur.spring_boot_blog.Entities.Slider;
import com.okanbahadirsoygur.spring_boot_blog.library.SSecurity;
import com.okanbahadirsoygur.spring_boot_blog.repos.AdminRepos;
import com.okanbahadirsoygur.spring_boot_blog.repos.CategoriesRepos;
import com.okanbahadirsoygur.spring_boot_blog.repos.SettingsRepos;
import com.okanbahadirsoygur.spring_boot_blog.repos.SliderRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
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

    @Autowired
    AdminRepos adminRepos;


    /**
     * Yönetim Paneli ile ilgili işlemler bu Controller tarafından yapılır.
     */

    @GetMapping("/admin")
    public ModelAndView admin(HttpSession sessions){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView =  girisKontrol("/admin/index",sessions);

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
     * <p>Bu sayfa sadece post edildiğinde çalışır.</p>
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


    /**
     * <p>Bu sayfa sadece post edildiğinde çalışır.</p>
     * @param categorie
     * @param sessions
     * @return 1
     */
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


    /**
     * Kullanıcı /admin sayfasına gittiği zaman session kontrolü yapılacak ve eğer giriş yapmamış ise bu sayfaya yönlendirilecektir.
     * Eğer giriş yapılmış ise bu sayfaya gelmeyecek direk /admin sayfasına yönlendirilecek.
     * Giriş bilgileri admin adındaki sesionda tutulacaktır.
     */
    @GetMapping("/admin/login")
    public ModelAndView login(HttpSession session){

        //giriş kontrolü yapalım
        //eğer giriş yapmamış ise fonksiyon otomatik /admin/login sayfasına yollayacak. Giriş yapmış ise verdiğimiz default view ismine gidecek.
       return girisKontrol("/admin/index", session);


    }


    /**
     * <p>Bu sayfa post edildiğinde çalışır.</p>
     * "/admin/login" sayfasındaki form doldurulduktan sonra buraya post edilecek.
     * Burda kontrol işlemleri yapıldıktan sonra giriş başarılı ise "giriş" adında session oluşturulup "/admin" sayfasına yönlendirilecek.
     * "giris" adındaki sessionda admin'in bilgileri tutulacak.
     * @param email
     * @param passwd
     * @return
     */
    @PostMapping("/admin/login/access")
    public ModelAndView login_access(@RequestParam String email, String passwd, HttpSession sessions) throws NoSuchAlgorithmException {

        //Gelen şifreyi md5'e dönüştürmek için gerekli olan fonksiyonları içeriyor.
        SSecurity security = new SSecurity();
        ModelAndView mv = new ModelAndView();


        Admin admin = adminRepos.findByEmailandPass(email,security.StringToMd5(passwd));

        if(admin == null) {
            mv.addObject("q","No such membership found!");
            mv.setViewName("/admin/login");
            return  mv;
        } else {

            //2 boyutlu bir array list.
            //admin bilgilerini sessiona yükleyelim.
            String[][] giris = new String[4][2];

            giris[0][0] = "email";
            giris[0][1] = admin.getEmail();

            giris[1][0] = "id";
            giris[1][1] = admin.getId()+"";

            giris[2][0] = "pass";
            giris[2][1] = admin.getPass();

            giris[3][0] = "last_login";
            giris[3][1] = admin.getLast_login();

            sessions.setAttribute("giris", giris);

            //başarlı bir giriş yaptığını anlıyoruz. admin sayfasına atalım.
            mv.setViewName("admin/index");
            return mv;


        }

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


    /**
     * Burada kullanıcı admin sayfasına giriş yapmış ise hiç bir şey yapılmaz, giriş yapmamış ise /admin/login sayfasına yönlendirme işlemi yapılır.
     */
    public ModelAndView girisKontrol(String defaultView, HttpSession session){

        ModelAndView mv = new ModelAndView();

        //giriş sessiyon bilgisini kontrol edelim eğer içi dolu ise default view'e yönlendirelim. Boş ise /admin/login sayfasına yönlendirme işlemi yapalım

        String[][] giris = (String[][]) session.getAttribute("giris");


        //eğer giriş yapılmış ise default viewe yollayalım
        if(giris != null){

            mv.setViewName(defaultView);
            return mv;
        }else{
            //giriş yapmamış ise /admin/login sayfasına gitsin

            mv.setViewName("/admin/login");
            return mv;
        }

    }

}//class
