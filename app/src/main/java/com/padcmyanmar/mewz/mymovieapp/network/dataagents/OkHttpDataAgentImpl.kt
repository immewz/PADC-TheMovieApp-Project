//package com.padcmyanmar.mewz.mymovieapp.network.dataagents
//
//import android.os.AsyncTask
//import com.google.gson.Gson
//import com.padcmyanmar.mewz.mymovieapp.Utils.API_GET_NOW_PLAYING
//import com.padcmyanmar.mewz.mymovieapp.Utils.BASE_URL
//import com.padcmyanmar.mewz.mymovieapp.Utils.MOVIE_API_KEY
//import com.padcmyanmar.mewz.mymovieapp.network.responses.MovieListResponse
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.util.concurrent.TimeUnit
//
//object OkHttpDataAgentImpl: MovieDataAgent {
//
//    private val mClient: OkHttpClient = OkHttpClient.Builder()
//        .connectTimeout(15,TimeUnit.SECONDS)
//        .readTimeout(15,TimeUnit.SECONDS)
//        .writeTimeout(15,TimeUnit.SECONDS)
//        .build()
//
//
//    override fun getNowPlayingMovies() {
//        GetNowPlayingMovieOkHttpTask(mClient).execute()
//    }
//
//    class GetNowPlayingMovieOkHttpTask(
//        private val  mOkHttpClient: OkHttpClient
//        ):AsyncTask<Void, Void, MovieListResponse>() {
//        override fun doInBackground(vararg void: Void?): MovieListResponse? {
//
//            val request = Request.Builder()
//                .url("""$BASE_URL$API_GET_NOW_PLAYING?apikey=$MOVIE_API_KEY&language=en-US&page=1""")
//                .build()
//
//            try {
//
//                val response = mOkHttpClient.newCall(request).execute()
//                if(response.isSuccessful){
//                    response.body?.let {
//                        val responseString = it.string()
//                        val response = Gson().fromJson(
//                            responseString,
//                            MovieListResponse::class.java
//
//                        )
//                        return response
//                    }
//                }
//
//            }catch (e: Exception){
//                e.printStackTrace()
//            }
//
//            return null
//        }
//
//        override fun onPostExecute(allNewsResponse: MovieListResponse?) {
//            super.onPostExecute(allNewsResponse)
//        }
//    }
//}