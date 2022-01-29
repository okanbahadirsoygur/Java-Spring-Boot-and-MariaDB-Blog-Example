package com.example.dort.Entities;

//JPA Java Persistance API (Hibernate)
import javax.persistence.*;

/*
    Veritabanındaki tablomuzu sisteme tanıtalım.
 */

@Entity
@Table(name = "urunler")

public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isim;

    private Double fiyat;


    /*
      1. İlgili tablodaki verileri listeleye bilmek için fonksiyonlarımızın isimlerinin get ile başlaması gerekmektedir, aksi taktirde hibernate fetchAll ile geriye ilgili kolonları döndürmeyecektir.
      2. Burada belirtilmeyen kolonlar hibernate tarafından yok sayılacaktır.
      3. Fonksiyon isimleri get ile başlar demiştik, get'den sonra yazılan isim iligili json tag'ının ismi olur. Örneğin: getfiyat için çıktı şu şekildedir(fiyat). [{"id":1,"isim":"Macbook M1","fiyat":20000.0}]
      4. Fonksiyon ismi ne ise geriye dönecek json tagları o olacaktır. Fonksiyon isimleri veritabanındaki kolon isimlerinden farklı olabilir.
     */

    public String getisim(){return isim;};

    public Double getfiyatt(){return fiyat;};

    public Long getId(){return id;}




    public void setIsim(String isim){this.isim = isim;}

    public void setFiyat(Double fiyat){this.fiyat = fiyat;}

}
