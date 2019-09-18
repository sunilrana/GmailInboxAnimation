package com.sunilrana.googleinboxanimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sunilrana.googleinboxanimation.FirstFragment
import com.sunilrana.googleinboxanimation.R

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.container, FirstFragment())
          .commit()
    }
  }

  override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount >= 1) {
      supportFragmentManager.popBackStack()
    } else {
      super.onBackPressed()
    }
  }
}