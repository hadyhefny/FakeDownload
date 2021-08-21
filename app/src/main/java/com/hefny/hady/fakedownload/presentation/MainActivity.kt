package com.hefny.hady.fakedownload.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.hefny.hady.fakedownload.FakeDownloadApplication
import com.hefny.hady.fakedownload.databinding.ActivityMainBinding
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
        viewModel.getFakeVideos()
    }

    private fun initRecyclerview() {
        adapter = VideosAdapter(this)
        binding.videosRecyclerview.adapter = adapter
    }

    private fun subscribeObserves() {
        viewModel.downloadVideoLiveData.observe(this) { dataResource ->
            dataResource.data?.let { item ->
                adapter.updateItem(item)
            }
            dataResource.error?.let { errorMsg ->
                showErrorToast(errorMsg)
            }
        }
        viewModel.fakeVideosLiveData.observe(this) { dataResource ->
            binding.progressbar.isVisible = dataResource.loading
            dataResource.data?.let { videos ->
                adapter.setVideos(videos)
            }
            dataResource.error?.let { errorMsg ->
                showErrorToast(errorMsg)
            }
        }
    }

    private fun showErrorToast(errorMessage: String){
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun downloadVideo(id: Int) {
        viewModel.downloadVideo(id)
    }

    override fun playVideo(url: String) {
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show()
    }
}