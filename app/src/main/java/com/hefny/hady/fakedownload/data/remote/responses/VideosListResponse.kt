package com.hefny.hady.fakedownload.data.remote.responses

data class VideosListResponse(
    val videoResponses: ArrayList<VideoItemResponse> = ArrayList()
) {
    init {
        videoResponses.add(
            VideoItemResponse(
                id = 1, type = "VIDEO",
                url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                name = "Video 1"
            )
        )
        videoResponses.add(
            VideoItemResponse(
                id = 2, type = "VIDEO",
                url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                name = "Video 2"
            )
        )
        videoResponses.add(
            VideoItemResponse(
                id = 3, type = "VIDEO",
                url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                name = "Video 3"
            )
        )
        videoResponses.add(
            VideoItemResponse(
                id = 4, type = "VIDEO",
                url = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                name = "Video 4"
            )
        )
    }
}