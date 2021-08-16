package com.example.rickandmortycorrect.data.repositories.pagingSource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycorrect.data.network.apiservice.EpisodeApiService
import com.example.rickandmortycorrect.models.RickAndMortyEpisode
import retrofit2.HttpException
import java.io.IOException

private const val EPISODE_STARTING_PAGE_INDEX = 1

class EpisodePagingSource(
    private val service: EpisodeApiService
) : PagingSource<Int, RickAndMortyEpisode>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyEpisode> {
        val position = params.key ?: EPISODE_STARTING_PAGE_INDEX
        return try {
            val response = service.getListEpisode(position)
            val next = response.info.next
            val nextNumber = if (next == null){
                null
            }else{
                Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            }
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyEpisode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
