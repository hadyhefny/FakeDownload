package com.hefny.hady.fakedownload.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hefny.hady.fakedownload.databinding.VideoListitemBinding
import com.hefny.hady.fakedownload.domain.models.VideoItem

class VideosAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var videosList: List<VideoItem> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            VideoListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = videosList[position]
        (holder as VideoViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    fun setVideos(videos: List<VideoItem>) {
        videosList = videos
        notifyDataSetChanged()
    }

    fun updateItem(item: VideoItem) {
        videosList.forEachIndexed { index, videoItem ->
            if (videoItem.id == item.id) {
                videoItem.downloaded = item.downloaded
                notifyItemChanged(index)
            }
        }
    }

    class VideoViewHolder(
        private val binding: VideoListitemBinding,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoItem) {
            binding.videoName.text = item.name
            binding.videoProgress.progress = item.getDownloadedPercentage()
            binding.downloadPercentage.text = "${item.getDownloadedPercentage()}%"
            if (item.getDownloadedPercentage() >= 0) {
                binding.videoProgress.visibility = View.VISIBLE
                binding.videoPlay.visibility = View.GONE
                binding.videoDownload.visibility = View.GONE
                binding.downloadPercentage.visibility = View.VISIBLE
            }
            if (item.getDownloadedPercentage() == 100) {
                binding.videoProgress.visibility = View.GONE
                binding.videoPlay.visibility = View.VISIBLE
                binding.videoDownload.visibility = View.GONE
                binding.downloadPercentage.visibility = View.GONE
            }
            itemView.setOnClickListener {
                if (item.getDownloadedPercentage() < 0) {
                    onItemClickListener.downloadVideo(item.id)
                }
                if (item.getDownloadedPercentage() == 100) {
                    onItemClickListener.playVideo(item.url)
                }
            }
        }
    }
}

interface OnItemClickListener {
    fun downloadVideo(id: Int)
    fun playVideo(url: String)
}