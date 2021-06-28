package io.indrian.whatmovies.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.indrian.whatmovies.data.models.Movie
import io.indrian.whatmovies.data.models.TVShow
import io.indrian.whatmovies.data.source.NetworkBoundResource
import io.indrian.whatmovies.data.source.local.LocalDataSource
import io.indrian.whatmovies.data.source.remote.ApiResponse
import io.indrian.whatmovies.data.source.remote.RemoteDataSource
import io.indrian.whatmovies.utils.AppExecutors
import io.indrian.whatmovies.vo.Resource

class Repository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    fun getMovies(): LiveData<Resource<PagedList<Movie>>> {
        return object : NetworkBoundResource<PagedList<Movie>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<Movie>> {
                return LivePagedListBuilder(localDataSource.getMovies(), pageConfig()).build()
            }

            override fun shouldFetch(data: PagedList<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> {
                return remoteDataSource.getMovies()
            }

            override fun saveCallResult(data: List<Movie>) {
                localDataSource.insertMovies(data)
            }
        }.asLiveData()
    }

    fun getTVShows(): LiveData<Resource<PagedList<TVShow>>> {
        return object : NetworkBoundResource<PagedList<TVShow>, List<TVShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TVShow>> {
                return LivePagedListBuilder(localDataSource.getTVShows(), pageConfig()).build()
            }

            override fun shouldFetch(data: PagedList<TVShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TVShow>>> {
                return remoteDataSource.getTVShows()
            }

            override fun saveCallResult(data: List<TVShow>) {
                localDataSource.insertTVShow(data)
            }
        }.asLiveData()
    }

    private fun pageConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(4)
        .setPageSize(4)
        .build()

//    suspend fun getTVShows(page: Int = 1): List<TVShow> = remoteDataSource.getTVShows(page)
//
//    suspend fun getDetailMovies(id: Long): Movie? = remoteDataSource.getDetailMovie(id)
//
//    suspend fun getDetailTVShow(id: Long): TVShow? = remoteDataSource.getDetailTVShow(id)
}