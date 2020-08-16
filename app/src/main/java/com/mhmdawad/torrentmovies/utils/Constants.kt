package com.mhmdawad.torrentmovies.utils

class Constants {

    companion object{
        const val BASE_URL = "https://yts.mx/api/v2/"

        fun getMoviesGenre(): MutableList<Pair<String, String>> {
            val genrePair: MutableList<Pair<String, String>> = mutableListOf()
            genrePair.add(Pair(" All ",""))
            genrePair.add(Pair("Comedy","Comedy"))
            genrePair.add(Pair("Science fiction","sci-fi"))
            genrePair.add(Pair("Horror","Horror"))
            genrePair.add(Pair("Romance","Romance"))
            genrePair.add(Pair("Action","Action"))
            genrePair.add(Pair("Thriller","Thriller"))
            genrePair.add(Pair("Drama","Drama"))
            genrePair.add(Pair("Mystery","Mystery"))
            genrePair.add(Pair("Crime","Crime"))
            genrePair.add(Pair("Animation","Animation"))
            genrePair.add(Pair("Adventure","Adventure"))
            genrePair.add(Pair("Fantasy","Fantasy"))
            genrePair.add(Pair("Comedy-Romance","Comedy-Romance"))
            genrePair.add(Pair("Action-Comedy","Action-Comedy"))
            genrePair.add(Pair("Superhero","Superhero"))
            return genrePair
        }
    }

}