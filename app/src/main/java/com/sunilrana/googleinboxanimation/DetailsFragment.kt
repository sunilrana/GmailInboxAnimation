package com.sunilrana.googleinboxanimation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DetailsFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.detail, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

//    val content = view.findViewById<View>(R.id.content).also { it.alpha = 0f }
//
//    ObjectAnimator.ofFloat(content, View.ALPHA, 0f, 1f).apply {
//      startDelay = 50
//      duration = 150
//      start()
//    }
  }
}
