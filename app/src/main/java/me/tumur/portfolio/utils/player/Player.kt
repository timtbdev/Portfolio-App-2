package me.tumur.portfolio.utils.player

import android.content.Context
import android.content.ContextWrapper
import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory


class Player(private var context: Context, private val playerView: PlayerSurface) {

    private var player: ExoPlayer? = null
    private var playbackPosition: Long = 0
    private var currentWindowIndex: Int = 0
    private var playWhenReady: Boolean = true
    private var currUri: Uri? = null


    private val lifecycleObserver = PlayerLifeCycleObserver(this)


    init {

        while (context !is LifecycleOwner) {
            context = (context as ContextWrapper).baseContext
        }
        lifecycleObserver.registerLifecycle((context as LifecycleOwner).lifecycle)
    }

    //Play single video
    fun play(uri: Uri?) {
        if (uri == null) return
        currUri = uri
        initPlayer()
        preparePlayer(currUri!!)
    }

    private fun initPlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(context)
            loadState()
            playerView.player = player
        } else {
            loadState()
        }
    }

    //Build MediaSource for one video and prepare player
    private fun preparePlayer(uri: Uri) {
        val mediaSource = MediaSourceBuilder().build(uri)
        player?.prepare(mediaSource, true, false)
    }

    //Overloaded function to build MediaSource for whole playlist and prepare player
    private fun preparePlayer(uriList: Array<Uri>) {
        val mediaSource = MediaSourceBuilder().build(uriList)
        player?.prepare(mediaSource, true, false)
    }

    private fun saveState() {
        if (player != null) {
            playbackPosition = player?.currentPosition ?: 0L
            currentWindowIndex = player?.currentWindowIndex ?: 0
            playWhenReady = player?.playWhenReady ?: true
        }
    }

    private fun loadState() {
        player?.apply {
            playWhenReady = playWhenReady
            seekTo(currentWindowIndex, playbackPosition)
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            saveState()
            player?.release()
            player = null
        }
    }


    //region Handle Lifecycle

    fun start() {
        player?.playWhenReady = true
        player?.playbackState
        currUri ?: play(currUri)
    }

    fun resume() {
        player?.playWhenReady = true
        currUri ?: play(currUri)
        loadState()
    }

    fun pause() {
        player?.playWhenReady = false
        player?.playbackState
        saveState()
    }

    fun stop() {
        releasePlayer()
        player?.stop(true)
    }
}