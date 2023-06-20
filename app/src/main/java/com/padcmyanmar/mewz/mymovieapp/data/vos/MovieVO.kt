package com.padcmyanmar.mewz.mymovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.mewz.mymovieapp.persistance.typeconverters.*

@Entity(tableName = "movies")
@TypeConverters(
    GenreListTypeConverter::class,
    CollectionTypeConverter::class,
    ProductionCompanyTypeConverter::class,
    ProductionCountryTypeConverter::class,
    SpokenLanguageTypeConverter::class
)
data class MovieVO(

    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @SerializedName("belongs_to_collection")
    @ColumnInfo(name = "belongs_to_collection")
    val belongsToCollection: CollectionVO?,

    @SerializedName("budget")
    @ColumnInfo(name = "budget")
    val budget: Double?,

    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres: List<GenreVO>?,

    @SerializedName("homepage")
    @ColumnInfo(name = "homepage")
    val homepage: String?,

    @SerializedName("id")
    @PrimaryKey
    val id: Int = 0,

    @SerializedName("imdb_id")
    @ColumnInfo(name = "imdb_id")
    val imdbId: String?,

    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies: List<ProductionCompaniesVO>?,

    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountries: List<ProductionCountriesVO>?,

    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @SerializedName("revenue")
    @ColumnInfo(name = "revenue")
    val revenue: Double?,

    @SerializedName("runtime")
    @ColumnInfo(name = "runtime")
    val runtime: Int?,

    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguage: List<SpokenLanguageVO>?,

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String?,

    @SerializedName("tagline")
    @ColumnInfo(name = "tagline")
    val tagline: String?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,

    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    @ColumnInfo(name = "type")
    var type: String?
) {
    fun getRatingBasedOnFiveStars(): Float {
        return voteAverage?.div(2)?.toFloat() ?: 0.0f
    }

    fun getGenresAsCommaSeparatedString(): String {
        return genres?.map { it.name }?.joinToString(",") ?: ""
    }

    fun getProductionCountriesAsCommaSeparatedString(): String {
        return productionCountries?.map { it.name }?.joinToString(",") ?: ""
    }
}

const val NOW_PLAYING = "NOW_PLAYING"
const val POPULAR = "POPULAR"
const val TOP_RATED = "TOP_RATED"


