package com.app.androidtvapp.ui.video

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.media3.ui.leanback.LeanbackPlayerAdapter
import com.app.androidtvapp.util.PlaybackSeekDiskDataProvider


class VideoPlayBackFragment : VideoSupportFragment() {
    private val URL =
        ("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
    val TAG = "VideoConsumptionWithExoPlayer"
    private lateinit var player: ExoPlayer

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = ExoPlayer.Builder(requireContext()).build()
        val playerView = PlayerView(requireContext())
        playerView.useController = true
        playerView.player = player;
        val mPlayerAdapter = LeanbackPlayerAdapter(requireContext(), player, 16)
        // Set up PlaybackTransportControlGlue
        val glue = PlaybackTransportControlGlue(requireContext(), mPlayerAdapter)

        val mediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(Uri.parse(URL)))
        glue.host = VideoSupportFragmentGlueHost(this);
//        PlaybackSeekDiskDataProvider.setDemoSeekProvider(glue);

        glue.playWhenPrepared();
        player.prepare(mediaSource)
//        player.seekTo(30000)
        glue.title = "ABC";
        glue.subtitle = "XYZ";

        // Set the view for the glue (PlayerView in this case)
        glue.play()
//        val playerGlue = PlaybackTransportControlGlue(getActivity(),player)
    }

}