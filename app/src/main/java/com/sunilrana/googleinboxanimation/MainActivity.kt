package com.sunilrana.googleinboxanimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sunilrana.googleinboxanimation.FirstFragment
import com.sunilrana.googleinboxanimation.R
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

  var abc : List<String> ? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val list = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")


    list.toObservable() // extension function for Iterables
            .filter { it.length >= 5 }
            .subscribe {
              println("@@@@@@@@   $it")

            }

    if (savedInstanceState == null) {
      supportFragmentManager
          .beginTransaction()
          .replace(R.id.container, FirstFragment())
          .commit()





    }

   // Observable.range(0,9).filter( a {})
//
//    Observable.range(0, 9).filter( it % 2 == 1).subscribe(::printEven)
//
//
//
//    fun printEven(value: Int) {
//
//        print(value)
//      }

//    val localTime = LocalDateTime.parse("2019-12-07T13:45:00-5:00")
//   val strDate = "2019-12-07T13:45:00 -5:00"
////  //  val localTime = OffsetDateTime.parse("2019-12-07T13:45:00-5:00")
////    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
////    val time = formatter.parse(strDate)
////    println("@@@@@ $time")
//
//
////    String input = "30 11 2012 12:08:56.235 +0700" ;
////    DateTimeFormatter f = DateTimeFormatter.ofPattern ( "dd MM uuuu HH:mm:ss.SSS X" , Locale.US );
////    OffsetDateTime odt = OffsetDateTime.parse ( input , f );
//
//    val f = DateTimeFormatter.ofPattern ( "yyyy-MM-dd'T'HH:mm:ss Z" , Locale.US )
//    val date = OffsetDateTime.parse ( strDate , f)
//    println("@@@@@ ${date.toString()}")
  }

  override fun onBackPressed() {
    if (supportFragmentManager.backStackEntryCount >= 1) {
      supportFragmentManager.popBackStack()
    } else {
      super.onBackPressed()
    }
  }



}