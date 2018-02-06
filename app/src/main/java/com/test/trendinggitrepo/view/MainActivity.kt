package com.test.trendinggitrepo.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.test.trendinggitrepo.R
import com.test.trendinggitrepo.adapter.RepoListAdapter
import com.test.trendinggitrepo.model.RepoResponse
import com.test.trendinggitrepo.network.OnResponseListener
import com.test.trendinggitrepo.network.RestAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var repos: ArrayList<RepoResponse.Repo> = ArrayList()
    var repoListAdapter: RepoListAdapter = RepoListAdapter(this, repos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repoList.layoutManager = LinearLayoutManager(this)
        repoList.adapter = repoListAdapter

        getAndroidTrendingRepos() // make initial network call

        swipeLayout.setOnRefreshListener {
            getAndroidTrendingRepos()
            swipeLayout.isRefreshing = false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount <= 0) {
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    private fun getAndroidTrendingRepos() {
        RestAdapter.GetTrendingAndroid(this, object : OnResponseListener<RepoResponse> {

            override fun onSuccess(response: RepoResponse) {
                repos.clear()
                repos.addAll(response.items)
                repoListAdapter.notifyDataSetChanged()
            }

            override fun onFailure(failure: String) {
                Toast.makeText(this@MainActivity,failure,Toast.LENGTH_LONG).show()
            }
        })
    }
}
