package com.example.dort.Entities;

//JPA Java Persistance API (Hibernate)
import javax.persistence.*;


/*
    Veritabanından sayfaları çekmek, düzenlemek, eklemek için kullanacağımız Modelimiz(Entities)
 */

@Entity
@Table(name = "pages")

public class Pages {

     /*
      1. İlgili tablodaki verileri listeleye bilmek için fonksiyonlarımızın isimlerinin get ile başlaması gerekmektedir, aksi taktirde hibernate fetchAll ile geriye ilgili kolonları döndürmeyecektir.
      2. Burada belirtilmeyen kolonlar hibernate tarafından yok sayılacaktır.
      3. Fonksiyon isimleri get ile başlar demiştik, get'den sonra yazılan isim iligili json tag'ının ismi olur. Örneğin: getfiyat için çıktı şu şekildedir(fiyat). [{"id":1,"isim":"Macbook M1","fiyat":20000.0}]
      4. Fonksiyon ismi ne ise geriye dönecek json tagları o olacaktır. Fonksiyon isimleri veritabanındaki kolon isimlerinden farklı olabilir.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long subId;

    private Long categorieId;

    private String slug;

    private String title;

    private String subTitle;

    private String data;

    private String seo_1;

    private String seo_2;

    private Long rank;

    private String createdTime;

    private Long deleted;


    //getter metodları.

    public Long getSubId() {
        return subId;
    }


    public Long getCategorieId() {
        return categorieId;
    }


    public String getSlug() {
        return slug;
    }


    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }


    public String getData() {
        return data;
    }


    public String getSeo_1() {
        return seo_1;
    }


    public String getSeo_2() {
        return seo_2;
    }


    public Long getRank() {
        return rank;
    }


    public String getCreatedTime() {
        return createdTime;
    }


    public Long getDeleted() {
        return deleted;
    }

    public Long getId() {
        return id;
    }



    //setter metodları.

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }


    public void setSlug(String slug) {
        this.slug = slug;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public void setData(String data) {
        this.data = data;
    }


    public void setSeo_1(String seo_1) {
        this.seo_1 = seo_1;
    }


    public void setSeo_2(String seo_2) {
        this.seo_2 = seo_2;
    }


    public void setRank(Long rank) {
        this.rank = rank;
    }


    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }
}
