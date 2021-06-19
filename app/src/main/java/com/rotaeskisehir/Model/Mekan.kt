package com.rotaeskisehir.Mekan

class Mekan {

    var mekanprofile: String? = null
    var yoltarif: String? = null
    var yorumlar : String? = null
    var puan: Int? = null

    constructor(){}
    constructor(mekanprofile:String,yoltarif:String,yorumlar:String,puan:Int){
        //constructor içinde mekanlar için gerekli nesneleri sınıfımız içinde tanımlıyoruz.
        this.mekanprofile = mekanprofile
        this.yoltarif = yoltarif
        this.yorumlar = yorumlar
        this.puan = puan
    }

}