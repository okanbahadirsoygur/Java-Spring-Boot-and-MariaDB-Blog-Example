package com.okanbahadirsoygur.spring_boot_blog.Entities;

import javax.persistence.*;

@Table(name = "slider")
@Entity
public class Slider {

    /**
     * <p>@GeneratedValue(strategy=GenerationType.IDENTITY)</p>
     * <p>Eğer bu şekilde annotation tanımlaması yapmaz isek yeni satır ekler iken; SliderRepos.save(); dediğimiz zaman bizden id değeri bekleyecektir.</p>
     * <p>Oysa ki bizim id değerimiz otomatik artan bir değer olduğu için bizim girmemize gerek yok. Bu annotation id değerini otomatik artmasını ve bizim id değeri girmememizi sağlıyor</p>
     *
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String img_url;

    private int rank;

    private String url;



    //getter metodları
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getUrl() {
        return url;
    }


    public int getRank() {
        return rank;
    }




    //setter metodları

    public void setId(Long id) {
        this.id = id;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }


    public void setUrl(String url) {
        this.url = url;
    }
}
