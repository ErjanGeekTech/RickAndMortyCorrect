package com.example.rickandmortycorrect.data.repositories.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickandmortycorrect.constants.Constants.DEFAULT_PAGE_INDEX
import com.example.rickandmortycorrect.data.db.AppDatabase
import com.example.rickandmortycorrect.data.network.apiservice.LocationApiService
import com.example.rickandmortycorrect.models.RemoteKeys
import com.example.rickandmortycorrect.models.RickAndMortyLocation
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class LocationMediator @Inject constructor(
    val characterApiService: LocationApiService,
    val appDatabase: AppDatabase
) : RemoteMediator<Int, RickAndMortyLocation>() {

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, RickAndMortyLocation>
    ): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                pageKeyData as Int
            }
        }

        try {
            val response = characterApiService.getListLocation(page, state.config.pageSize)
            val isEndOfList = response.results.isEmpty()

            appDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getRepoDao().clearRemoteKeys()
                    appDatabase.locationDao().clearRemoteKeys()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    RemoteKeys(repoId = it.id.toString(), prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.getRepoDao().insertAll(keys)
                appDatabase.locationDao().insertAll(response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: retrofit2.HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    /**
     * this returns the page key or the final end of list success result
     */
    suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, RickAndMortyLocation>
    ): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                remoteKeys?.nextKey?.plus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                MediatorResult.Success(endOfPaginationReached = true)
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, RickAndMortyLocation>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { doggo -> appDatabase.getRepoDao().remoteKeysDoggoId(doggo.id.toString()) }
    }



    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, RickAndMortyLocation>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.getRepoDao().remoteKeysDoggoId(repoId.toString())
            }
        }
    }

}