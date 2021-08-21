package com.hefny.hady.fakedownload.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hefny.hady.fakedownload.R
import com.hefny.hady.fakedownload.data.remote.responses.VideoItemResponse

class VideosAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var videosList: List<VideoItemResponse> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.video_listitem, parent, false)
        return VideoViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = videosList[position]
        (holder as VideoViewHolder).bind(item)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    fun setVideos(videos: List<VideoItemResponse>) {
        videosList = videos
        notifyDataSetChanged()
    }

    fun updateItem(id: Int, downloadedPercentage: Int) {
        videosList.forEachIndexed { index, videoItem ->
            if (videoItem.id == id) {
                videoItem.downloadedPercentage = downloadedPercentage
                notifyItemChanged(index)
            }
        }
    }

    class VideoViewHolder(view: View, private val onItemClickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(view) {
        private val name: TextView = itemView.findViewById(R.id.video_name)
        private val progressBar: ProgressBar = itemView.findViewById(R.id.video_progress)
        private val playImage: ImageView = itemView.findViewById(R.id.video_play)
        private val downloadImage: ImageView = itemView.findViewById(R.id.video_download)
        private val downloadPercentage: TextView = itemView.findViewById(R.id.download_percentage)
        fun bind(item: VideoItemResponse) {
            name.text = item.name
            progressBar.progress = item.downloadedPercentage
            downloadPercentage.text = "${item.downloadedPercentage}%"
            if (item.downloadedPercentage >= 0) {
                progressBar.visibility = View.VISIBLE
                playImage.visibility = View.GONE
                downloadImage.visibility = View.GONE
                downloadPercentage.visibility = View.VISIBLE
            }
            if (item.downloadedPercentage == 100) {
                progressBar.visibility = View.GONE
                playImage.visibility = View.VISIBLE
                downloadImage.visibility = View.GONE
                downloadPercentage.visibility = View.GONE
            }
            itemView.setOnClickListener {
                if (item.downloadedPercentage < 0) {
                    onItemClickListener.onItemClicked(item.id)
                }
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(id: Int)
}