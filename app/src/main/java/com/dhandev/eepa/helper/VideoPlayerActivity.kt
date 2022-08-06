package com.dhandev.eepa.helper

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.dhandev.eepa.R
import com.dhandev.eepa.databinding.ActivityVideoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.Util

class VideoPlayerActivity : AppCompatActivity() {

    private val binding by lazy(LazyThreadSafetyMode.NONE){
        ActivityVideoPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }

    var player : ExoPlayer? = null

    private fun initializePlayer(){
        val link = intent.getStringExtra("link")

        player = ExoPlayer.Builder(this@VideoPlayerActivity).build().also { exoPlayer ->

            binding.videoView.player = exoPlayer

            if (link != null){
                val mediaItem = MediaItem.fromUri(link)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
            }
        }
    }

    private fun releasePlayer(){
        player?.release()
        player = null
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24){
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hydeSystemUI()
        if (Util.SDK_INT < 24 && player == null) {
            initializePlayer()
        }
    }

    private fun hydeSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.videoView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }


    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 24) {
            releasePlayer()
        }
    }


    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }
}