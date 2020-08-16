package com.mhmdawad.torrentmovies.ui.fragments.details

import android.app.AlertDialog
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.CastItem
import com.mhmdawad.torrentmovies.data.model.Movie
import com.mhmdawad.torrentmovies.utils.*
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.movie_quality_dialog.view.*
import org.koin.android.viewmodel.ext.android.getViewModel


class DetailsFragment : Fragment(R.layout.fragment_details), YouTubePlayer.OnFullscreenListener,
    IOnBackPressed, QualityListener {


    private lateinit var ytPlayer: YouTubePlayer
    private var ytFullScreen = false
    private lateinit var viewModel: DetailsViewModel
    private val args: DetailsFragmentArgs by navArgs()
    private val ytFragment by lazy { childFragmentManager.findFragmentById(R.id.trailerView) as YouTubePlayerSupportFragmentX? }
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        viewModel = getViewModel()
        viewModel.getMovieDetails(args.movieId)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsMovieCover.transitionName = resources.getString(R.string.transitionName)
        observeObservers()
    }


    private fun observeObservers() {
        viewModel.getMovieDetails().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    detailsNoInternet.gone()
                    detailsContainer.show()
                }
                is Resource.Loaded -> {
                    detailsContainer.show()
                    detailsNoInternet.gone()
                    showMovieDetails(it.data?.data?.movie!!)
                }
                is Resource.Error -> {
                    detailsContainer.gone()
                    detailsNoInternet.show()
                }
            }
        })
    }

    private fun showMovieDetails(movie: Movie) {
        with(movie) {
            detailsMovieBackground.downloadImage(backgroundImageOriginal)
            detailsMovieCover.downloadImage(mediumCoverImage)
            detailsMovieName.text = titleEnglish
            movieCategory.addCategories(genres!!)
            movieMpaRating.textOrGone(mpaRating)
            movieDescription.text = descriptionFull
            initYoutubePlayer(ytTrailerCode!!)
            movieRatingTxt.text = rating.toString()
            initRecyclerViews(
                cast,
                listOf(
                    mediumScreenshotImage1!!,
                    mediumScreenshotImage2!!,
                    mediumScreenshotImage3!!
                )
            )
            playMovieFAB.setOnClickListener {
                showMovieQualityDialog(this, requireView())
            }
            detailsBackArrow.setOnClickListener{
                findNavController().popBackStack()
            }
        }
    }

    private fun initRecyclerViews(castList: List<CastItem>, screenShotImages: List<String>) {
        if (castList.isEmpty())
            castLayout.gone()
        else
            castRV.adapter = MovieCastAdapter(castList)
        screenShotsRV.adapter = ScreenShotsAdapter(screenShotImages)
    }

    private fun initYoutubePlayer(ytTrailerCode: String) {
        ytFragment?.initialize(
            DeveloperKey.DEVELOPER_API,
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    wasRestored: Boolean
                ) {
                    try {
                        if (ytTrailerCode.isEmpty())
                            throw IllegalArgumentException()
                        ytPlayer = player!!
                        ytPlayer.setOnFullscreenListener(this@DetailsFragment)
                        if (!wasRestored)
                            ytPlayer.cueVideo(ytTrailerCode)
                    } catch (e: IllegalArgumentException) {
                        youtubeLayout.gone()
                    }
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    showToast(resources.getString(R.string.checkYoutube))
                }
            })

    }

    private fun showMovieQualityDialog(movieData: Movie?, view: View) {
        val viewGroup: ViewGroup? = view.findViewById(android.R.id.content)
        val dialogView: View =
            LayoutInflater.from(view.context).inflate(
                R.layout.movie_quality_dialog
                , viewGroup, false
            )
        val builder = AlertDialog.Builder(view.context)
        builder.setTitle(resources.getString(R.string.movieQuality))
        builder.setView(dialogView)
        alertDialog = builder.create()
        val movieAdapter = MovieDialogAdapter(
            movieData?.torrents!!,
            "${movieData.titleEnglish} ${movieData.year}", this
        )
        dialogView.movieQualityRV.apply {
            adapter = movieAdapter
        }
        alertDialog.show()
    }

    override fun onStart() {
        super.onStart()
        changeStatusBar()
    }


    private fun changeStatusBar() {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }


    override fun onFullscreen(screen: Boolean) {
        ytFullScreen = screen
    }

    override fun onBackPressed():Boolean {
        if (ytFullScreen)
            ytPlayer.setFullscreen(false)
        else
            findNavController().popBackStack()
        return false
    }

    override fun selectQuality(movieUrl: String, movieName: String) {
        findNavController().navigate(
            DetailsFragmentDirections.actionDetailsFragmentToStreamFragment(
                movieUrl,
                movieName
            )
        )
        alertDialog.dismiss()
    }
}