package com.hiqmalism.mysubmission

import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_PLAYER = "key_player"
    }

    private lateinit var detailCard: ImageView
    private lateinit var detailName: TextView
    private lateinit var detailCountry: TextView
    private lateinit var detailClub: TextView
    private lateinit var detailPosition: TextView
    private lateinit var detailSummary: TextView

    private lateinit var detailPace: TextView
    private lateinit var detailShoot: TextView
    private lateinit var detailPass: TextView
    private lateinit var detailDribble: TextView
    private lateinit var detailDef: TextView
    private lateinit var detailPhy: TextView

    private lateinit var paceProgress: ProgressBar
    private lateinit var shootProgress: ProgressBar
    private lateinit var passProgress: ProgressBar
    private lateinit var dribbleProgress: ProgressBar
    private lateinit var defProgress: ProgressBar
    private lateinit var phyProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupViews()
        setData()
    }

    private fun setupViews() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setTitle(R.string.player_stats)
        }

        detailCard = findViewById(R.id.detail_card)
        detailName = findViewById(R.id.detail_name)
        detailCountry = findViewById(R.id.detail_country)
        detailClub = findViewById(R.id.detail_club)
        detailPosition = findViewById(R.id.detail_position)
        detailSummary = findViewById(R.id.detail_summary)

        detailPace = findViewById(R.id.detail_pace)
        detailShoot = findViewById(R.id.detail_shoot)
        detailPass = findViewById(R.id.detail_pass)
        detailDribble = findViewById(R.id.detail_dribble)
        detailDef = findViewById(R.id.detail_defence)
        detailPhy = findViewById(R.id.detail_physical)

        paceProgress = findViewById(R.id.pace_progress)
        shootProgress = findViewById(R.id.shoot_progress)
        passProgress = findViewById(R.id.pass_progress)
        dribbleProgress = findViewById(R.id.dribble_progress)
        defProgress = findViewById(R.id.defence_progress)
        phyProgress = findViewById(R.id.physical_progress)
    }

    private fun setData() {
        val player = intent.getParcelableExtra<Player>(KEY_PLAYER)

        player?.let {
            detailName.text = it.name
            detailCountry.text = it.country
            detailClub.text = it.club
            detailPosition.text = it.position
            detailSummary.text = it.summary

            detailPace.text = it.pace.toString()
            detailShoot.text = it.shoot.toString()
            detailPass.text = it.pass.toString()
            detailDribble.text = it.dribble.toString()
            detailDef.text = it.def.toString()
            detailPhy.text = it.phy.toString()
            Glide.with(this)
                .load(it.bg)
                .into(detailCard)

            setStat(detailPace, paceProgress, it.pace)
            setStat(detailShoot, shootProgress, it.shoot)
            setStat(detailPass, passProgress, it.pass)
            setStat(detailDribble, dribbleProgress, it.dribble)
            setStat(detailDef, defProgress, it.def)
            setStat(detailPhy, phyProgress, it.phy)
        }
    }

    private fun setStat(statTextView: TextView, progressBar: ProgressBar, statValue: Int) {
        statTextView.text = statValue.toString()
        progressBar.progress = statValue

        val color = if (statValue < 70) {
            ContextCompat.getColor(this, R.color.md_theme_secondary)
        } else {
            ContextCompat.getColor(this, R.color.md_theme_tertiary)
        }

        statTextView.setTextColor(color)
        setProgressBarColor(progressBar, color)
    }

    private fun setProgressBarColor(progressBar: ProgressBar, color: Int) {
        val progressDrawable = progressBar.progressDrawable as LayerDrawable
        val progressLayer = progressDrawable.findDrawableByLayerId(android.R.id.progress) as ClipDrawable
        val shape = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressLayer.drawable as GradientDrawable
        } else {
            TODO("VERSION.SDK_INT < M")
        }
        shape.setColor(color)
        progressBar.progressDrawable = progressDrawable
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
