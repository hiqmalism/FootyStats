package com.hiqmalism.mysubmission

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val name: String,
    val profile: String,
    val card: Int,
    val bg: Int,
    val country: String,
    val club: String,
    val position: String,
    val summary: String,
    val pace: Int,
    val shoot: Int,
    val pass: Int,
    val dribble: Int,
    val def: Int,
    val phy: Int,
) : Parcelable
