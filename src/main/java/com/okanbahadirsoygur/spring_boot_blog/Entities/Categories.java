package com.okanbahadirsoygur.spring_boot_blog.Entities;


import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {


     /*
      1. İlgili tablodaki verileri listeleye bilmek için fonksiyonlarımızın isimlerinin get ile başlaması gerekmektedir, aksi taktirde hibernate fetchAll ile geriye ilgili kolonları döndürmeyecektir.
      2. Burada belirtilmeyen kolonlar hibernate tarafından yok sayılacaktır.
      3. Fonksiyon isimleri get ile başlar demiştik, get'den sonra yazılan isim iligili json tag'ının ismi olur. Örneğin: getfiyat için çıktı şu şekildedir(fiyat). [{"id":1,"isim":"Macbook M1","fiyat":20000.0}]
      4. Fonksiyon ismi ne ise geriye dönecek json tagları o olacaktır. Fonksiyon isimleri veritabanındaki kolon isimlerinden farklı olabilir.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String slug;

    private String description;

    private Long rank;

    private Long deleted;

    //getter metodları
    public Long getId() {
        return id;
    }

    public String getSlug() {return slug;}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getRank() {
        return rank;
    }

    public Long getDeleted() {
        return deleted;
    }



    //setter metodları


    public void setSlug(String slug) {this.slug = slug;}

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setRank(Long rank) {
        this.rank = rank;
    }

    public void setDeleted(Long deleted) {this.deleted = deleted;}
}
