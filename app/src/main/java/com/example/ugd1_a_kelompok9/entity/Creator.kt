package com.example.ugd1_a_kelompok9.entity

class Creator (var nama: String, var npm: String){

    companion object{
        @JvmField
        var listOfCreator = arrayOf(
            Creator("Andreas Noah Jati Sesoca", "200710610"),
            Creator("Almindo Savior Tiranda Rammang", "200710662"),
            Creator("Octa Dian Kristanti Kainakaimu", "200710829")
        )
    }
}