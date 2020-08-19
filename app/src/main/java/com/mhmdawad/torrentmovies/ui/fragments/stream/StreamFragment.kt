package com.mhmdawad.torrentmovies.ui.fragments.stream

import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.github.se_bastiaan.torrentstream.StreamStatus
import com.github.se_bastiaan.torrentstream.Torrent
import com.github.se_bastiaan.torrentstream.TorrentStream
import com.github.se_bastiaan.torrentstream.listeners.TorrentListener
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.*
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.masterwok.opensubtitlesandroid.models.OpenSubtitleItem
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.*
import com.mhmdawad.torrentmovies.utils.rv_listeners.SubtitleListener
import kotlinx.android.synthetic.main.exo_player_control_view.*
import kotlinx.android.synthetic.main.fragment_stream.*
import kotlinx.android.synthetic.main.movie_quality_dialog.view.*
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.core.KoinComponent
import org.koin.core.get
import java.io.File


class StreamFragment : Fragment(R.layout.fragment_stream), KoinComponent, TorrentListener,
    SubtitleListener, Player.EventListener {

    private val args: StreamFragmentArgs by navArgs()
    private lateinit var simplePlayer: SimpleExoPlayer
    private lateinit var torrentStream: TorrentStream
    private lateinit var mergeMediaSource: MergingMediaSource
    private lateinit var mediaSource: ExtractorMediaSource
    private lateinit var alertDialog: AlertDialog
    private lateinit var viewModel: StreamViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        viewModel = getViewModel()
        clearNoLimitFlag()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTorrentStream()
        observeObservers()
        viewsListener()
    }

    private fun viewsListener() {

        moviePlayer.setControllerVisibilityListener {
            with(activity?.window?.decorView) {
                if (it == 0)
                    this?.systemUiVisibility = showSystemUI()
                else
                    this?.systemUiVisibility = hideSystemUI()
            }
        }

        movieSubtitle.setOnClickListener {
            viewModel.searchMovieSubtitle(args.movieName)
        }
    }

    private fun observeObservers() {
        viewModel.getSubtitlesData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    progressContainer.show()
                }
                is Resource.Loaded -> {
                    showMovieSubtitlesDialog(it.data!!, requireView())
                    progressContainer.gone()
                }
                is Resource.Error -> {
                    progressContainer.gone()
                    showToast(it.msg!!)
                }
            }
            simplePlayer.stopPlayer()
        })

        viewModel.getSubtitleStatus().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loaded -> {
                    alertDialog.dismiss()
                    addSubtitleToPlayer(it.data)
                }
                is Resource.Error -> showToast("Error with adding subtitle, Please try again..")
            }
        })
    }


    private fun showMovieSubtitlesDialog(listOfSubtitles: Array<OpenSubtitleItem>, view: View) {
        val viewGroup: ViewGroup? = view.findViewById(android.R.id.content)
        val dialogView =
            LayoutInflater.from(view.context).inflate(
                R.layout.movie_quality_dialog
                , viewGroup, false
            )
        AlertDialog.Builder(view.context).apply {
            setTitle(resources.getString(R.string.movieSubtitle))
            setView(dialogView)
            alertDialog = create()
        }
        dialogView.movieQualityRV.apply {
            addDividers()
            adapter = MovieSubtitlesAdapter(listOfSubtitles, this@StreamFragment)
        }
        alertDialog.show()
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
        mediaSource = ExtractorMediaSource.Factory(factory).createMediaSource(Uri.parse(path))
        mergeMediaSource = MergingMediaSource(mediaSource)
        simplePlayer.apply {
            startPlayer(mergeMediaSource)
            addListener(this@StreamFragment)
        }
    }

    private fun addSubtitleToPlayer(data: Uri?) {
        val factory: DefaultDataSourceFactory = get()
        val textFormat: Format = get()
        val textMediaSource = SingleSampleMediaSource.Factory(factory)
            .createMediaSource(data, textFormat, C.TIME_UNSET)
        mergeMediaSource = MergingMediaSource(mediaSource, textMediaSource)
        simplePlayer.addingSubtitle(mergeMediaSource, simplePlayer.currentPosition)
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        simplePlayer.seekPlayer(simplePlayer.contentPosition, mergeMediaSource)
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playWhenReady && playbackState == Player.STATE_READY)
            progressContainer.gone()
        else if (playWhenReady && playbackState == Player.STATE_BUFFERING)
            progressContainer.show()
    }

    override fun onStreamReady(torrent: Torrent?) {
        initPlayer(torrent?.videoFile?.absolutePath!!)
    }

    override fun onStreamProgress(torrent: Torrent?, status: StreamStatus?) {
        try {
            streamSeeds.formatText(R.string.streamSeeds, status?.seeds)
            streamSpeed.formatText(R.string.streamDownloadSpeed, status?.downloadSpeed?.div(1024))
            streamProgressTxt.formatText(R.string.streamProgress, status?.progress, "%")
        } catch (e: IllegalStateException) {
            println("ERROR: $e")
        }
    }

    override fun onPause() {
        super.onPause()
        if (this::simplePlayer.isInitialized)
            simplePlayer.stopPlayer()

    }

    override fun onResume() {
        super.onResume()
        if (this::simplePlayer.isInitialized)
            simplePlayer.resumePlayer()

    }

    override fun onStop() {
        super.onStop()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
        torrentStream.removeListener(this)
        if (this::simplePlayer.isInitialized)
            simplePlayer.release()
    }

    override fun onSubtitleClicked(subtitle: OpenSubtitleItem) {
        viewModel.downloadSubtitle(
            subtitle,
            Uri.fromFile(File("${activity?.getExternalFilesDir(null)?.absolutePath}/${subtitle.SubFileName}"))
        )

    }

    override fun onStreamPrepared(torrent: Torrent?) {
    }

    override fun onStreamStopped() {
    }

    override fun onStreamStarted(torrent: Torrent?) {
    }

    override fun onStreamError(torrent: Torrent?, e: Exception?) {
        println("Error: $e")
    }
}