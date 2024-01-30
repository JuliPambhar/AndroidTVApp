package com.app.androidtvapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.lifecycle.asLiveData
import com.app.androidtvapp.data.local.Category
import com.app.androidtvapp.data.remote.MovieItem
import com.app.androidtvapp.util.Resourse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : RowsSupportFragment() {

    private val viewModel: ListFragmentViewModel by activityViewModels()
    private val rootAdapter by lazy { ArrayObjectAdapter(ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
//            prepareEntranceTransition()
        }
        this.adapter = rootAdapter

        setOnItemViewSelectedListener { _, item, _, _ ->
            if (item is MovieItem) {
                viewModel.onItemSelected(item)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieResponse.asLiveData().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resourse.Idle -> {
                }

                is Resourse.Loading -> {

                }

                is Resourse.Success -> {
                    println("respose data ${resource.data.size}")
                    displayData(resource.data)
                    //                    startEntranceTransition()
                }

                is Resourse.Error -> {
                    print("data: $resource")
                }
            }
        }
    }

    private fun displayData(categories: List<Category>) {
        categories.forEach { category ->
            val rowAdapter = ArrayObjectAdapter(PosterPresenter())
            val movieItems = mutableListOf<MovieItem>()
            category.movies.forEach { movieItem ->
                movieItems.add(movieItem)
            }
            rowAdapter.addAll(0, movieItems)
            val headerItem = HeaderItem(category.id, category.genre)
            val row = ListRow(headerItem, rowAdapter)
            rootAdapter.add(row)
        }

        /* for (category in categories) {
             val headerItem = HeaderItem(category.id, category.genre)

             for (movie in category.movies) {
                 rawAdapter.add(movie)
             }
             rootAdapter.add(ListRow(headerItem, rawAdapter))
             println("adapter size ${adapter.size()}")
         }*/
    }
}