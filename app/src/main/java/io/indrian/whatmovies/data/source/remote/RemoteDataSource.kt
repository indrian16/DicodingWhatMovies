package io.indrian.whatmovies.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.remote.responses.ListResponse
import io.indrian.whatmovies.data.source.remote.services.MovieService
import io.indrian.whatmovies.data.source.remote.services.TVShowService
import io.indrian.whatmovies.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RemoteDataSource(
    private val movieService: MovieService,
    private val tvShowService: TVShowService
){

    fun getMovies(page: Int = 1): LiveData<ApiResponse<List<Movie>>> {
        EspressoIdlingResource.increment()
        val liveMovies = MutableLiveData<ApiResponse<List<Movie>>>()
        val request = movieService.getMovies(page)
        request.enqueue(object : Callback<ListResponse<Movie>> {
            override fun onResponse(
                call: Call<ListResponse<Movie>>,
                response: Response<ListResponse<Movie>>
            ) {
                Timber.d("getMovies: $response")
                liveMovies.value = ApiResponse.success(
                    response.body()?.results ?: arrayListOf()
                )
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ListResponse<Movie>>, t: Throwable) {
                Timber.e("getMoviesError: ${t.message}")
                liveMovies.value = ApiResponse.error(t.message.toString(), arrayListOf())
                EspressoIdlingResource.decrement()
            }
        })

        return liveMovies
    }
    fun getDetailMovie(id: Long): LiveData<ApiResponse<Movie>> {
        EspressoIdlingResource.increment()
        val liveMovie = MutableLiveData<ApiResponse<Movie>>()
        val request = movieService.getDetailMovie(id)

        request.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                Timber.d("getDetailMovie: $response")
                val body = response.body() ?: Movie()
                val ids = body.genres.map { it.id }
                body.genreIds = ids
                liveMovie.value = ApiResponse.success(
                    body = body
                )
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<Movie?>, t: Throwable) {
                Timber.e("getDetailMovie: ${t.message}")
                liveMovie.value = ApiResponse.error(t.message ?: "Something Error", Movie())
                EspressoIdlingResource.decrement()
            }
        })

        return liveMovie
    }

    fun getTVShows(page: Int = 1): LiveData<ApiResponse<List<TVShow>>> {
        EspressoIdlingResource.increment()
        val liveTVShows = MutableLiveData<ApiResponse<List<TVShow>>>()
        val request = tvShowService.getTVShows(page)
        request.enqueue(object : Callback<ListResponse<TVShow>> {
            override fun onResponse(
                call: Call<ListResponse<TVShow>>,
                response: Response<ListResponse<TVShow>>
            ) {
                Timber.d("getTVShows: $response")
                liveTVShows.value = ApiResponse.success(
                    body = response.body()?.results ?: arrayListOf()
                )
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<ListResponse<TVShow>>, t: Throwable) {
                Timber.e("getTVShows: ${t.message}")
                liveTVShows.value = ApiResponse.error(t.message.toString(), arrayListOf())
                EspressoIdlingResource.decrement()
            }
        })

        return liveTVShows
    }

    fun getDetailTVShow(id: Long): LiveData<ApiResponse<TVShow>> {
        EspressoIdlingResource.increment()
        val liveTVShow = MutableLiveData<ApiResponse<TVShow>>()
        val request = tvShowService.getDetailTVShow(id)

        request.enqueue(object : Callback<TVShow?> {
            override fun onResponse(call: Call<TVShow?>, response: Response<TVShow?>) {
                Timber.d("getDetailTVShow: $response")
                val body = response.body() ?: TVShow()
                val ids = body.genres.map { it.id }
                body.genreIds = ids
                liveTVShow.value = ApiResponse.success(
                    body = body
                )
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShow?>, t: Throwable) {
                Timber.e("getDetailTVShow: ${t.message}")
                liveTVShow.value = ApiResponse.error(t.message ?: "Something Error", TVShow())
                EspressoIdlingResource.decrement()
            }
        })

        return liveTVShow
    }
}