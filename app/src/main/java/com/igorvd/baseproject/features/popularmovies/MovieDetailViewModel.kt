package com.igorvd.baseproject.features.popularmovies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.igorvd.baseproject.domain.movies.GetMoviesInteractor
import com.igorvd.baseproject.domain.movies.MovieSortBy
import com.igorvd.baseproject.domain.movies.entities.Movie
import com.igorvd.baseproject.domain.movies.repository.MovieRepository
import com.igorvd.baseproject.domain.utils.extensions.withIOContext
import com.igorvd.baseproject.features.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Igor Vilela
 * @since 30/07/18
 */
class MovieDetailViewModel
@Inject
constructor() : BaseViewModel() {


}