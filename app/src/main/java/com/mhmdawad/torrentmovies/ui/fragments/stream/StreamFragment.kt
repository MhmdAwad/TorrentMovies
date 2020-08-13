package com.mhmdawad.torrentmovies.ui.fragments.stream

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.ui.SubtitleView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Log
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.*
import kotlinx.android.synthetic.main.fragment_stream.*
import org.koin.core.KoinComponent
import org.koin.core.get


class StreamFragment : Fragment(R.layout.fragment_stream), KoinComponent, TorrentListener {

    private val args: StreamFragmentArgs by navArgs()
    private lateinit var simplePlayer: SimpleExoPlayer
    private lateinit var torrentStream: TorrentStream

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeStatusBar()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        initTorrentStream()
    }

    private fun initTorrentStream() {
        torrentStream = get()
        torrentStream.startStream(args.movieUrl)
        torrentStream.addListener(this)
    }

    private fun initPlayer(path: String) {
        simplePlayer = get()
        moviePlayer.player = simplePlayer
        val factory: DefaultDataSourceFactory = get()
        val mediaSource = ExtractorMediaSource.Factory(factory).createMediaSource(Uri.parse(path))
        simplePlayer.apply {
            startPlayer(mediaSource)
            addListener(object : Player.EventListener {
                override fun onPlayerError(error: ExoPlaybackException?) {
                    val pos = simplePlayer.contentPosition
                    simplePlayer.startPlayer(mediaSource)
                    simplePlayer.seekTo(pos)
                }

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    if (playWhenReady && playbackState == Player.STATE_READY)
                        progressContainer.gone()
                    else if (playWhenReady && playbackState == Player.STATE_BUFFERING)
                        progressContainer.show()
                }
            })
        }
        moviePlayer.setControllerVisibilityListener {
            with(activity?.window?.decorView) {
                if (it == 0)
                    this?.systemUiVisibility = showSystemUI()
                else
                    this?.systemUiVisibility = hideSystemUI()
            }
        }

        val subtitleView =
            activity?.findViewById(com.google.android.exoplayer2.R.id.exo_subtitles) as SubtitleView
//        subtitleView.visibility = View.GONE

    }

    private fun hideSystemUI(): Int {
        return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    private fun showSystemUI(): Int {
        return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }


    override fun onStreamReady(torrent: Torrent?) {
        initPlayer(torrent?.videoFile?.absolutePath!!)

    }

    override fun onStreamPrepared(torrent: Torrent?) {
    }

    override fun onStreamStopped() {
    }

    override fun onStreamStarted(torrent: Torrent?) {
    }

    override fun onStreamProgress(torrent: Torrent?, status: StreamStatus?) {
        streamSeeds.text = String.format(resources.getString(R.string.streamSeeds), status?.seeds)
        streamSpeed.text = String.format(
            resources.getString(R.string.streamDownloadSpeed),
            status?.downloadSpeed?.div(1024)
        )
        streamProgressTxt.text =
            String.format(resources.getString(R.string.streamProgress), status?.progress, "%")
    }

    override fun onStreamError(torrent: Torrent?, e: Exception?) {
        Log.i("TAG", "onStreamError $e")
    }

    override fun onPause() {
        super.onPause()
        try {
            simplePlayer.stopPlayer()
        } catch (e: UninitializedPropertyAccessException) {
            println("ERROR: $e")
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            simplePlayer.resumePlayer()
        } catch (e: UninitializedPropertyAccessException) {
            println("ERROR: $e")
        }
    }
    private fun changeStatusBar() {
        activity?.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (this::simplePlayer.isInitialized)
            simplePlayer.release()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        torrentStream.removeListener(this)
    }
}