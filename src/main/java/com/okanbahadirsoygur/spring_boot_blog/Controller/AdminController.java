package com.okanbahadirsoygur.spring_boot_blog.Controller;

import com.okanbahadirsoygur.spring_boot_blog.Entities.*;
import com.okanbahadirsoygur.spring_boot_blog.library.SSecurity;
import com.okanbahadirsoygur.spring_boot_blog.repos.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    @Autowired
    PagesRepos pagesRepos;

    @Autowired
    FileRepos fileRepos;


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


        modelAndView =  girisKontrol("admin/settings",sessions);

        modelAndView.addObject("slug_settings",true);
        modelAndView.addObject("settings",ayarlariGetir());



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

        modelAndView =  girisKontrol("/admin/sliders",sessions);

        modelAndView.addObject("slug_sliders", true);
        modelAndView.addObject("sliders", sliderlariGetir());



        return modelAndView;

    }


    @GetMapping("/admin/categories")
    public ModelAndView categories(HttpSession sessions){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView =  girisKontrol("/admin/categories",sessions);

        modelAndView.addObject("slug_categories",true);
        modelAndView.addObject("categories", kategoriGetir());


        return modelAndView;

    }



    @GetMapping("/admin/pages")
    public ModelAndView pages(HttpSession sessions, @RequestParam(defaultValue = "",required = false) String q){

        ModelAndView modelAndView = new ModelAndView();

        modelAndView =  girisKontrol("/admin/pages",sessions);

        modelAndView.addObject("slug_pages",true);
        modelAndView.addObject("pages", sayfaGetir());
        modelAndView.addObject("q", q);


        return modelAndView;
    }


    /**
     * Bu sayfada seçilen sayfalar düzenlenir, yada yeni sayfa eklenir.
     * id değeri -1 ise yeni sayfa eklenmek isteniyordur. id Değeri -1 harici bir değer ise var olan bir sayfa düzenlenmek isteniyordur.
     *
     * @param id
     * @return
     */
    @GetMapping("/admin/pages/edit_or_add/{id}")
    public ModelAndView pages_edit_or_add(@PathVariable Long id, HttpSession sessions){

        ModelAndView modelAndView = new ModelAndView();


        modelAndView =  girisKontrol("/admin/pages_edit_or_add",sessions);
        modelAndView.addObject("slug_pages",true);


        if(id != -1){
            //dolu ise var olan sayfanın bilgileri çekelim ve viewe yollayalım ekrana bassın.
            Pages  page = new Pages();

            //ilgli sayfayı repostoryden isteyelim
            page =  pagesRepos.getById(id);

            modelAndView.addObject("page",page);
            modelAndView.addObject("id",id);
            modelAndView.addObject("title","Edit page");

        }else{
            //id değeri boş ise yeni sayfa eklenmek isteniyordur.
            //boş bir pages objesi yaratalım. Yoksa ön yüzde değerleri çekerken hata veriyor. Böylece boş objenin boş(null) datalarını çekebiliyor.
            Pages page = new Pages();
            modelAndView.addObject("page",page);

            modelAndView.addObject("title","Add new page");
        }

        modelAndView.addObject("kategoriler", kategoriGetir());





        return  modelAndView;

    }


    /**
     * Bu sayfa sadece post edildiğinde çalışır.
     * "/admin/pages/edit_or_add/{id}" sayfasından gelen datalar buraya post edilir.
     * id değeri boş ise yeni sayfa oluşturulur, eğer id değeri dolu ise var olan sayfa update edilir.
     *  Request param ile form datasını yakalıyoruz.
     * @param id
     * @return
     */
    @PostMapping("/admin/pages/edit_or_add/post")
    public RedirectView pages_edit_or_add_post(@RequestParam Long id, String slug, String title, String sub_title, String short_data, String data, String seo1, String seo2, String img_url, Long categorie){

        RedirectView rv = new RedirectView();

        //eğer gelen id değeri -1 değil ise var olan sayfa editlenmek isteniyordur.
        if(id != -1){

            rv.setUrl("/admin/pages?q=Updated as page");

            //id si olan sayfayı repostory'den isteyelim.
            Pages pages = pagesRepos.getById(id);

            pages.setTitle(title);
            pages.setData(data);
            pages.setShort_data(short_data);
            pages.setSubTitle(sub_title);
            pages.setImgUrl(img_url);
            pages.setSeo_1(seo1);
            pages.setSeo_2(seo2);
            pages.setSlug(slug);
            pages.setRank(0L);
            pages.setCategorieId(categorie);




            //repostry'nin vermiş olduğu sayfayı düzenledikten sonra kaydedilim.
            pagesRepos.save(pages);

            

        }else{
            //yeni sayfa yaratılmak isteniyordur.

            //boş bir pages objesi oluşturalım.
            Pages pages = new Pages();

            pages.setTitle(title);
            pages.setData(data);
            pages.setShort_data(short_data);
            pages.setSubTitle(sub_title);
            pages.setImgUrl(img_url);
            pages.setSeo_1(seo1);
            pages.setSeo_2(seo2);
            pages.setSlug(slug);
            pages.setRank(0L);
            pages.setCategorieId(categorie);

            pagesRepos.save(pages);

            rv.setUrl("/admin/pages?q=New page added");
        }

        return rv;

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


    @PostMapping(value = "/admin/pages/delete")
    public String pages_delete(@RequestBody Pages pages, HttpSession sessions){

        pagesRepos.deleteById(Long.valueOf(pages.getId()));

        return "deleted";
        // return  new RedirectView("/admin/settings");


    }



    @PostMapping(value = "/admin/pages/update")
    public int pages_update(@RequestBody Pages pages, HttpSession sessions){

        pagesRepos.updatePagesById(pages.getId(), pages.getTitle(), pages.getRank());

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


    /**
     * Bu sayfada dosya yükleme işlemi yapılmaz sadece sisteme yüklenen(upload) dosyalar listelenir.
     *
     * Dosya yüklenmek istendiği zaman "/admin/media/add" sayfasına post ile yüklenmek istenen dosya yönlendirilir.
     * @return
     */
    @GetMapping("/admin/media")
    public ModelAndView media(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/media");

        return modelAndView;
    }


    /**
     * Bu sayfa sadece post edildiğinde çalışır.
     * "admin/post" sayfasından gelen file nesnesi buraya post ile yollanır.
     * MultipartFile nesnesi ile gelen dosyayı tutuyoruz. Daha sonra binary'e çevirip veritabanındaki data kolonuna yolluyoruz.
     * data kolonumuzun türü longblob bende buraada yeni öğrendim bu türü. İçerisinde 4gb lık binary tutabiliyor.
     * application.properties içerisinde;
     *      spring.servlet.multipart.maxFileSize = 4000MB
     *      spring.servlet.multipart.maxRequestSize= 4000MB
     *      tanımlamaları ile max yüklenen bilecek data boyutunu 4gb olarak ayarladım. Ama bu seferde java VM heap hatası alıyorum. byte[] değişkeni memory yetersizliği nedeniyle patlıyor. Bi ara bu sorunu çözerim.
     *      Bende default heap değeri Maximum JVM memory: 2147483648  bu şekilde. Şualık test amaçlı yeterli.
     *      Heap sorununu çözsek bile MariaDB max 64MB lık data alabiliyor. Daha fazla yollar isek; "(conn=176) Could not send query: query size is >= to max_allowed_packet (16777216)"  hatası veriyor. Emininm bir çözüm yolu vardır ama uğraşmak istemiyorum. 64mb gayet yeterli basit bir blog sistemi için.
     * File Entity de "private byte[] data;" kolonun annotation'unu  @Lob olarak tanıttık ki içerisine binary data gideceğini bilsin.
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/admin/media/add")
    public String media_add(@RequestParam  MultipartFile file) throws Exception{

        byte[] bytes = file.getBytes();
        File fileEntity = new File();
        String clearFileName = file.getOriginalFilename();

        //dosya ismini biraz temizleyelim.
        clearFileName = clearFileName.replace(" ","");
        clearFileName = clearFileName.replace("'","");
        clearFileName = clearFileName.replace("?","");


        fileEntity.setData(bytes);
        fileEntity.setName(clearFileName);
        fileRepos.save(fileEntity);

        return "1";
    }


    /**
     * Veritabanına yüklenen binary dosyalara bu url üzerinden erişilir.
     * Geriye binary data döndürür. Bu binary datayı javascript ile tutup(stream) indirmek gerekiyor.
     * Neden veritabanına binary data yüklüyorum. Çünkü embeded tomcat üzerinde virtual folder aramak istemedim. Local'e tomcat kurulacak sonra virtual folder tanımlanacak.... çok iş. Böylesi daha basit.
     * Bu yapının dez avantajı geriye bir url döndürmemesi. Gelen data binary olduğu için bunu url' ile çekemiyoruz ve url yapısında paylaşamıyoruz. Sadece depolama için kullanıyoruz. Bu yüzden web sitesindeki resimleri buraya yükleyip çekemeyiz.
     * ResponseEntity olarak geriye değer döndürdüğüm için sayfa artık bir html sayfası değil.
     * Headers'inde attachment olarak geçiyor. Böylece url'den id yi isteyince direk body içerisindeki data download ediliyor. Farklı bir örnek oldu.
     * Bu örnek canlı sistemde pek bir işe yaramaz. Ama yazmış bulundum. O yüzden bırakıyorum bu şekilde.
     * @return
     */
    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "id") Long id)throws  Exception{
        File file  = fileRepos.getById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());

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

    public List<Pages> sayfaGetir(){

        List<Pages> pagesList = new ArrayList<>();
        pagesRepos.getPagesOrderBySira().forEach(pagesList::add);

        return pagesList;

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
