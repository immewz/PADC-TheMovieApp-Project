//package com.padcmyanmar.mewz.mymovieapp.network.dataagents
//
//import android.os.AsyncTask
//import android.util.Log
//import com.google.gson.Gson
//import com.padcmyanmar.mewz.mymovieapp.Utils.API_GET_NOW_PLAYING
//import com.padcmyanmar.mewz.mymovieapp.Utils.BASE_URL
//import com.padcmyanmar.mewz.mymovieapp.Utils.MOVIE_API_KEY
//import com.padcmyanmar.mewz.mymovieapp.data.vos.MovieVO
//import com.padcmyanmar.mewz.mymovieapp.network.responses.MovieListResponse
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.net.HttpURLConnection
//import java.net.URL
//
//object MovieDataAgentImpl: MovieDataAgent {
//
//    override fun getNowPlayingMovies() {
//        GetNowPlayingMovieTask().execute()
//    }
//
//    class GetNowPlayingMovieTask(): AsyncTask<Void, Void, MovieListResponse?>() {
//        override fun doInBackground(vararg params: Void?): MovieListResponse? {
//
//            val url: URL  // https://api.themoviedb.org/3/movie/now_playing?api_key=4661fb0564bda3ee000675158b950273&language=en-US&page=1
//            var reader: BufferedReader? = null // reader: BufferReader
//            val stringBuilder: StringBuilder // stringBuilder:
//
//            try {
//
//                // create the HttpURLConnection
//                url = URL("""$BASE_URL$API_GET_NOW_PLAYING?apikey=$MOVIE_API_KEY&language=en-US&page=1""") // 1
//
//                val connection = url.openConnection() as HttpURLConnection //2 connection: "com.android.okhttp.intension"
//
//                // Set HTTP Method
//                connection.requestMethod = "GET" // 3
//
//                // give it 15 seconds to response
//                connection.readTimeout = 15 * 1000 // 4
//
//                connection.doInput = true // 5
//                connection.doOutput = false // 6
//
//                connection.connect() // 7
//
//                // read the output from the server
//                reader = BufferedReader(
//                    InputStreamReader(connection.inputStream)
//                ) // 8
//
//                stringBuilder = StringBuilder()
//
//                for (line in reader.readLines()){ // reader = BufferedReader
//                    stringBuilder.append(line + "\n")
//                }
//
//                val responseString = stringBuilder.toString()
//                Log.d("NowPlayingMovies", responseString)
//
//                val movieListResponse = Gson().fromJson(
//                    responseString,
//                    MovieListResponse::class.java
//                )
//
//                return  movieListResponse
//
//            }catch (e:Exception){
//                e.printStackTrace()
//                Log.e("NewsError", e.message?: "")
//            }finally {
//                if(reader != null){
//                    try {
//                        reader.close()
//                    }catch (ioe: IOException){
//                        ioe.printStackTrace()
//                    }
//                }
//            }
//
//            return null
//        }
//
//        override fun onPostExecute(result: MovieListResponse?) {
//            super.onPostExecute(result)
//        }
//
//    }
//
//
//}