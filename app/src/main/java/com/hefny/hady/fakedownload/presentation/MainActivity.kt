package com.hefny.hady.fakedownload.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.hefny.hady.fakedownload.FakeDownloadApplication
import com.hefny.hady.fakedownload.R
import com.hefny.hady.fakedownload.data.remote.responses.VideosListResponse
import com.hefny.hady.fakedownload.utils.Constants
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private lateinit var adapter: VideosAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FakeDownloadApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.videos_recyclerview)
        subscribeObserves()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        adapter = VideosAdapter(this)
        recyclerView.adapter = adapter
        adapter.setVideos(VideosListResponse().videoResponses)
    }

    private fun subscribeObserves() {
        viewModel.videosLiveData.observe(this) { dataResource ->
            dataResource.data?.let { downloadedMegaBytes ->
                val currentPercentage =
                    (downloadedMegaBytes * 100) / Constants.FAKE_VIDEO_SIZE_IN_MEGA_BYTES
                dataResource.id?.let { itemId ->
                    adapter.updateItem(itemId, currentPercentage)
                }
            }
        }
    }

    override fun onItemClicked(id: Int) {
        viewModel.downloadVideo(id)
    }
}