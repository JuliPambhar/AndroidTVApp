package com.app.androidtvapp.ui.video

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.app.androidtvapp.R
import com.app.androidtvapp.databinding.FragmentVideoBinding


class VideoActivity : FragmentActivity() {
    private val URL =
        ("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")

    private lateinit var binding: FragmentVideoBinding

    private val playBackFragment = VideoPlayBackFragment()

    @SuppressLint("UnsafeOptInUsageError")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val player = ExoPlayer.Builder(this).build()
// Bind the player to the view.
        /*binding.playerView.player = player

        // Build the media item.
        val mediaItem = MediaItem.fromUri(URL)
// Set the media item to be played.
        player.setMediaItem(mediaItem)

// Prepare the player.
        player.prepare()
// Start the playback.
        player.play()*/
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.play_back_fragment, playBackFragment)
        transaction.commit()
    }

    }