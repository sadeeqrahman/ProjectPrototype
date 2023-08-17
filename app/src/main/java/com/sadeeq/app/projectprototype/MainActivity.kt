package com.sadeeq.app.projectprototype

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadeeq.app.projectprototype.adapters.LoadMoreAdapter
import com.sadeeq.app.projectprototype.adapters.MoviesAdapter
import com.sadeeq.app.projectprototype.base.BaseActivity
import com.sadeeq.app.projectprototype.databinding.ActivityMainBinding
import com.sadeeq.app.projectprototype.utils.permissions.PermissionManager
import com.sadeeq.app.projectprototype.viewModels.ApiVIewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.apply {

            lifecycleScope.launchWhenCreated {
                viewModel.moviesList.collect {
                    moviesAdapter.submitData(it)

                }
            }

            moviesAdapter.addLoadStateListener { loadState ->
                val refreshState = loadState.refresh
                if (refreshState is LoadState.Error) {
                    val exception = refreshState.error
                    if (exception is HttpException) {
                        val errorCode = exception.code()
                        Toast.makeText(this@MainActivity, errorCode.toString(), Toast.LENGTH_LONG)
                            .show()
                    } else {
                        val errorMessage = "An error occurred: ${exception}"
                        Toast.makeText(
                            this@MainActivity,
                            errorMessage.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            moviesAdapter.setOnItemClickListener {
                startActivity(Intent(this@MainActivity, RoomMainActivity::class.java))
            }

            lifecycleScope.launchWhenCreated {
                moviesAdapter.loadStateFlow.collect {
                    val state = it.refresh
                    prgBarMovies.isVisible = state is LoadState.Loading

                }
            }


            rlMovies.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = moviesAdapter
            }

            rlMovies.adapter = moviesAdapter.withLoadStateFooter(
                LoadMoreAdapter {
                    moviesAdapter.retry()
                }
            )

        }


    }

}