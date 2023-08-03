package com.sadeeq.app.projectprototype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.roomDatabase.MovieEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomMainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_main)

        lifecycleScope.launchWhenCreated {
            roomViewModel.getAllMovies().collect {
                Toast.makeText(this@RoomMainActivity, it.size.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        val movieList = listOf(
            MovieEntity("Movie 1", "https://example.com/movie1.jpg"),
            MovieEntity("Movie 2", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 4", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 5", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 6", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 7", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 8", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 9", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 10", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 11", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 12", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 13", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 14", "https://example.com/movie2.jpg"),
            MovieEntity("Movie 15", "https://example.com/movie2.jpg"),
        )

        lifecycleScope.launch {
            roomViewModel.insertMovies(movieList)
        }
    }
}