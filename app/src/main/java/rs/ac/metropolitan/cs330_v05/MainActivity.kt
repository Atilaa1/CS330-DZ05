package rs.ac.metropolitan.cs330_v05

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var s = "Life cicle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(s, "Pokrećem onCreate() događaj")
    }

    override fun onStart() {
        super.onStart()
        Log.d(s, "Pokrećem onStart() događaj")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(s, "Pokrećem onRestart() događaj")
    }

    override fun onResume() {
        super.onResume()
        Log.d(s, "Pokrećem onResume() događaj")
    }

    override fun onPause() {
        super.onPause()
        Log.d(s, "Pokrećem onPause() događaj")
    }

    override fun onStop() {
        super.onStop()
        Log.d(s, "Pokrećem onStop() događaj")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(s, "Pokrećem onDestroy() događaj")
    }
}

