package com.hefny.hady.fakedownload.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.hefny.hady.fakedownload.FakeDownloadApplication
import com.hefny.hady.fakedownload.R
import com.hefny.hady.fakedownload.data.remote.responses.VideosListResponse
import com.hefny.hady.fakedownload.data.toVideoItem
import com.hefny.hady.fakedownload.databinding.ActivityMainBinding
import com.hefny.hady.fakedownload.domain.models.VideoItem
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnItemClickListener {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    private lateinit var adapter: VideosAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as FakeDownloadApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        subscribeObserves()
    }

    private fun initRecyclerview() {
        adapter = VideosAdapter(this)
        binding.videosRecyclerview.adapter = adapter
        val videoItems: ArrayList<VideoItem> = ArrayList()
        VideosListResponse().videoResponses.forEach {
            videoItems.add(it.toVideoItem())
        }
        adapter.setVideos(videoItems)
    }

    private fun subscribeObserves() {
        viewModel.downloadVideoLiveData.observe(this) { dataResource ->
            dataResource.data?.let { item ->
                adapter.updateItem(item)
            }
        }
    }

    override fun downloadVideo(id: Int) {
        viewModel.downloadVideo(id)
    }

    override fun playVideo(url: String) {
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show()
    }
}