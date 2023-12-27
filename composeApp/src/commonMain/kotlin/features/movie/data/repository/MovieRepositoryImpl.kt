package features.movie.data.repository

import app.cash.paging.*
import features.movie.data.datasource.remote.MovieApi
import features.movie.data.model.MovieModel
import features.movie.domain.repository.MovieRepository
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository() {
    override suspend fun getMovieList(params: Int?): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(api, params)
            }
        ).flow
    }
}

class MoviePagingSource(private val api: MovieApi, private val genreId: Int?) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val currentPage = params.key ?: 1
            val movies = api.getMovieList(page = currentPage, genreId = genreId)
            LoadResult.Page(
                data = movies.results,
                prevKey = null,
                nextKey = movies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}