package me.tumur.portfolio.utils.player

import android.net.Uri
import com.google.android.exoplayer2.source.DynamicConcatenatingMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import me.tumur.portfolio.utils.constants.Constants

class MediaSourceBuilder {

    //Build various MediaSource depending upon the type of Media for a given video/audio uri
    fun build(uri: Uri): MediaSource {
        val userAgent = Constants.USER_AGENT
        val lastPath = uri.lastPathSegment ?: ""

        val defaultHttpDataSourceFactory = DefaultHttpDataSourceFactory(userAgent)

        if (lastPath.contains(Constants.FORMAT_MP3) || lastPath.contains(Constants.FORMAT_MP4)) {

            return ExtractorMediaSource.Factory(defaultHttpDataSourceFactory)
                .createMediaSource(uri)

        } else if (lastPath.contains(Constants.FORMAT_M3U8)) {

            return HlsMediaSource.Factory(defaultHttpDataSourceFactory)
                .createMediaSource(uri)

        } else {
            val dashChunkSourceFactory = DefaultDashChunkSource.Factory(defaultHttpDataSourceFactory)

            return DashMediaSource.Factory(dashChunkSourceFactory, defaultHttpDataSourceFactory)
                .createMediaSource(uri)

        }
    }


    //Overloaded function to Build various MediaSource for whole playlist of video/audio uri
    fun build(uriList: Array<Uri>): MediaSource {
        val playlistMediaSource = DynamicConcatenatingMediaSource()
        uriList.forEach { playlistMediaSource.addMediaSource(build(it)) }
        return playlistMediaSource
    }
}