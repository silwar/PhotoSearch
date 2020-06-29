package com.silwar.photosearch.home

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silwar.photosearch.R
import com.silwar.photosearch.common.BaseActivity
import com.silwar.photosearch.home.adapter.PhotoListAdapter
import com.silwar.photosearch.home.model.Photo
import com.silwar.photosearch.util.*
import kotlinx.android.synthetic.main.activity_photo_search.*

class PhotoSearchActivity : BaseActivity() {

    private lateinit var viewModel: PhotoSearchViewModel

    private var photoAdapter: PhotoListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_search)

        viewModel = ViewModelProvider(this).get(PhotoSearchViewModel::class.java)

        initialiseRecyclerView()
        initialiseObservers()

        swipe_refresh_photo.setOnRefreshListener {
            val searchText = edit_photo_search.text.toString()
            if (searchText.isNotEmpty()) {
                photoAdapter?.reset()
                hideKeyboard()
                viewModel.refreshPhotos()
            } else {
                showToast(getString(R.string.error_empty_search_text))
            }
        }

        recycler_photo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val gridLayoutManager: GridLayoutManager? =
                    recyclerView.layoutManager as? GridLayoutManager
                gridLayoutManager?.run {
                    val position = this.findLastCompletelyVisibleItemPosition()
                    if (this.findLastCompletelyVisibleItemPosition() == photoAdapter?.itemCount!! - 1) {
                        viewModel.loadMorePhotos()
                    }
                }
            }
        })

        edit_photo_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            }
            false
        }
        button_photo_search.setOnClickListener {
            performSearch()
        }

    }

    private fun performSearch() {
        if (networkCompat.isOnline()) {
            val searchText = edit_photo_search.text.toString()
            if (searchText.isNotEmpty()) {
                hideKeyboard()
                photoAdapter?.reset()
                viewModel.searchPhoto(searchText)
            } else {
                showToast(getString(R.string.error_empty_search_text))
            }
        } else {
            showToast(getString(R.string.no_network_connectivity))
        }
    }


    private fun initialiseRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
//        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycler_photo.layoutManager = layoutManager
        photoAdapter = PhotoListAdapter(arrayListOf())
        recycler_photo.adapter = photoAdapter
    }

    private fun initialiseObservers() {
        viewModel.getError().observe(this, Observer {
            if (it.isNotHandled()) {
                showError(
                    getString(R.string.error_common_title),
                    it.peekContent().message,
                    positiveButton = DialogButton("Ok")
                )
            }
        })

        viewModel.getPhotoList().observe(this, Observer {
            if (it.isNotHandled()) {
                val response = it.peekContent()
                if (response.isNullOrEmpty()) {
                    text_photo_search_error.show()
                    swipe_refresh_photo.hide()
                } else {
                    text_photo_search_error.hide()
                    swipe_refresh_photo.show()

                    showPhotos(response as ArrayList<Photo>)
                }
            }
        })

        viewModel.isRefreshing().observe(this, Observer {
            if (it.isNotHandled()) {
                val isRefreshing = it.peekContent()
                if (!isRefreshing) {
                    swipe_refresh_photo.isRefreshing = false
                }
            }
        })
        viewModel.isLoading().observe(this, Observer {
            if (it.isNotHandled()) {
                val value = it.peekContent()
                progress_photo_search.show(value)
            }
        })

        viewModel.getWarning().observe(this, Observer {
            if (it.isNotHandled() && it.peekContent().isNotEmpty()) {
                showToast(it.peekContent())
            }
        })

        viewModel.isLoadingMorePhotos().observe(this, Observer {
            if (it.isNotHandled()) {
                val value = it.peekContent()
                linear_load_more.show(value)
            }
        })
    }

    private fun showPhotos(list: ArrayList<Photo>?) {
        photoAdapter?.updateData(list)
    }
}