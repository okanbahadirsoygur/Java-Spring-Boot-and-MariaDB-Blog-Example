package com.example.dort.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "slider")
@Entity
public class Slider {
    @Id
    @Column(name = "id", nullable = false)
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
