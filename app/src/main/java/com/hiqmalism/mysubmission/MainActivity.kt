package com.hiqmalism.mysubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFootball: RecyclerView
    private val list = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.footy_stats)

        rvFootball = findViewById(R.id.rv_football)
        rvFootball.setHasFixedSize(true)

        list.addAll(getListPlayer())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(this)
        rvFootball.layoutManager = layoutManager
        val listPlayerAdapter = ListPlayerAdapter(list)
        rvFootball.adapter = listPlayerAdapter

        rvFootball.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
    }

    private fun getListPlayer(): ArrayList<Player> {
        val playerName = resources.getStringArray(R.array.player_name)
        val playerProfile = resources.getStringArray(R.array.player_profile)
        val playerCard = resources.obtainTypedArray(R.array.player_card)
        val playerBg = resources.obtainTypedArray(R.array.player_bg)
        val playerCountry = resources.getStringArray(R.array.player_country)
        val playerClub = resources.getStringArray(R.array.player_club)
        val playerPosition = resources.getStringArray(R.array.player_position)
        val playerSummary = resources.getStringArray(R.array.player_summary)
        val playerPace = resources.getIntArray(R.array.player_pace)
        val playerShoot = resources.getIntArray(R.array.player_shoot)
        val playerPass = resources.getIntArray(R.array.player_pass)
        val playerDribble = resources.getIntArray(R.array.player_dribble)
        val playerDef = resources.getIntArray(R.array.player_def)
        val playerPhy = resources.getIntArray(R.array.player_phy)
        val listPlayer = ArrayList<Player>()

        for (i in playerName.indices) {
            val player = Player(
                playerName[i], playerProfile[i],
                playerCard.getResourceId(i, -1),
                playerBg.getResourceId(i, -1),
                playerCountry[i], playerClub[i], playerPosition[i],
                playerSummary[i],
                playerPace[i], playerShoot[i],
                playerPass[i], playerDribble[i],
                playerDef[i], playerPhy[i])
            listPlayer.add(player)
        }

        playerCard.recycle()
        playerBg.recycle()

        return listPlayer
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intentAbout = Intent(this, AboutActivity::class.java)
                startActivity(intentAbout)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}