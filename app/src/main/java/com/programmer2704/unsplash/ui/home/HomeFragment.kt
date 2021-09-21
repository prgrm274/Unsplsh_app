package com.programmer2704.unsplash.ui.home

import android.content.Context.WINDOW_SERVICE
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.programmer2704.unsplash.ui.PhotosAdapter
import com.programmer2704.unsplash.ui.base.BaseFragment
import com.programmer2704.unsplash.utils.*
import dagger.hilt.android.AndroidEntryPoint
import programmer2704.unsplash.R
import programmer2704.unsplash.databinding.HomeFragmentBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding
        get() = HomeFragmentBinding::inflate

    private val viewModel: HomeViewModel by viewModels()

val photosAdapter by lazy { PhotosAdapter(ArrayList()) }

    var snackbar: Snackbar? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        setupViews()
        initObservations()
    }

    fun setupViews() {
        context?.let { ctx ->
            photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            //! Set di menu
            //b.recyclerPopularPhotos.layoutManager = LinearLayoutManager(ctx)//ng v
            b.recyclerPopularPhotos.adapter = photosAdapter

            // NestedScrollView
            b.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    viewModel.loadMorePhotos()
                }
            }

            // Input Text Search
            b.inputSearchPhotos.setEndIconOnClickListener {
                b.txtSearchPhotos.setText("")
                viewModel.fetchPhotos(1)
            }

            b.txtSearchPhotos.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    b.txtSearchPhotos.dismissKeyboard()
                    performSearch(b.txtSearchPhotos.text.toString())
                    true
                }
                false
            }
        }
    }

    fun setupViewsLinear() {
        context?.let { ctx ->
            photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            b.recyclerPopularPhotos.layoutManager = LinearLayoutManager(ctx)//ng v
            b.recyclerPopularPhotos.adapter = photosAdapter

            // NestedScrollView
            b.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    viewModel.loadMorePhotos()
                }
            }

            // Input Text Search
            b.inputSearchPhotos.setEndIconOnClickListener {
                b.txtSearchPhotos.setText("")
                viewModel.fetchPhotos(1)
            }

            b.txtSearchPhotos.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    b.txtSearchPhotos.dismissKeyboard()
                    performSearch(b.txtSearchPhotos.text.toString())
                    true
                }
                false
            }
        }
    }

    private fun performSearch(query: String) {
        b.txtSearchPhotos.setText(query)
        viewModel.searchPhotos(query)
    }

    fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    b.recyclerPopularPhotos.gone()
                    b.progressPhotos.visible()
                }

                is LoadingNextPageState -> {
                    b.progressPhotos.gone()
                    showToast(getString(R.string.message_load_photos_str))
                }

                is ContentState -> {
                    b.recyclerPopularPhotos.visible()
                    b.progressPhotos.gone()
                }

                is ErrorState -> {
                    b.progressPhotos.gone()
                    b.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.retry()
                    }
                }

                is ErrorNextPageState -> {
                    b.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.retry()
                    }
                }
            }
        }

        viewModel.photosListLiveData.observe(viewLifecycleOwner) { photos ->
            photosAdapter.updateItems(photos)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list_or_grid->{
                Toast.makeText(context, "menu", Toast.LENGTH_SHORT).show()
                b.recyclerPopularPhotos.apply {
                    if (layoutManager is LinearLayoutManager) {
                        setupViews()
                        initObservations()
                    } else if (layoutManager is StaggeredGridLayoutManager ||
                        layoutManager is GridLayoutManager) {
                        setupViewsLinear()
                        initObservations()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getStaggeredLayoutManager(): StaggeredGridLayoutManager {
        val display = (context?.getSystemService(WINDOW_SERVICE) as WindowManager)
                .defaultDisplay

        val orientation = display.rotation
        if (orientation == Surface.ROTATION_90 ||
            orientation == Surface.ROTATION_270) {
            return StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        } else {
            return StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
