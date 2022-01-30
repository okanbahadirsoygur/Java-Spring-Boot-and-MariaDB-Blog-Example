var anasayfaSlider = new Swiper(".anasayfaSlider", {
    paginationClickable: true,
    loop:true,
    autoplay: {
        delay: 3000,
        disableOnInteraction: false,
    },
    pagination: {
        el: ".swiper-pagination",
    },

});





function arama(deger){



    //arama sonuçlarının gösterildiği div
    let arama_sonuc = document.querySelector(".arama-sonuc");
    //arama sonuçlarının ekrana basılacağı liste
    let arama_ul = document.querySelector(".arama-ul");

    //bütün sonuçları sıfırlayalım
    arama_ul.innerHTML = "";

    let q = deger.value;
    let gelen_data;

    fetch('/search/'+q)
        .then(response => response.json())
        .then(data => {
            gelen_data = data;
            console.log(gelen_data);

            //eğer data  boş değil ise
            if(gelen_data != null){


                for(let i = 0; i< gelen_data.length; i++){

                    arama_ul.innerHTML += "<li><a href='/pages/"+ gelen_data[i].slug+"'>"+ gelen_data[i].title + "</li></a>";
                }

                arama_sonuc.style.display = "block";



            } else{
                arama_sonuc.style.display = "none";
            }


            //arama alanı boş ise sonuç alanını gizleyelim.
            if(q == null || q == "" || q === undefined){
                arama_sonuc.style.display = "none";
            }

        });




}