package com.test.trendinggitrepo.adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.test.trendinggitrepo.R
import com.test.trendinggitrepo.model.RepoResponse
import com.test.trendinggitrepo.view.RepoDetailFragment


/**
 * Created by alexgomes on 2018-02-05.
 */
class RepoListAdapter(private val ctx: Context,
                      private val repoList: ArrayList<RepoResponse.Repo>) : RecyclerView.Adapter<RepoListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(v);
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        Glide.with(ctx)
                .load(repoList[position].owner.avatarUrl)
                .dontAnimate()
                .placeholder(R.mipmap.ic_launcher)
                .skipMemoryCache(true)
                .into(holder.repoImage)

        holder.repoTitle.text = repoList[position].name
        holder.repoDescription.text = repoList[position].description
        holder.repoForks.text = repoList[position].forks.toString()
        holder.repoStars.text = repoList[position].stars.toString()
    }

//    fun getAPosition(): Int{
//        return adapterPosition
//    }

    override fun getItemCount(): Int = repoList.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoImage = itemView.findViewById<ImageView>(R.id.repo_image)
        val repoTitle = itemView.findViewById<TextView>(R.id.repo_title)
        val repoDescription = itemView.findViewById<TextView>(R.id.repo_description)
        val repoForks = itemView.findViewById<TextView>(R.id.repo_forks)
        val repoStars = itemView.findViewById<TextView>(R.id.repo_stars)

        init {
            itemView.setOnClickListener {
                val transaction = (ctx as FragmentActivity).supportFragmentManager.beginTransaction()
                transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                transaction.replace(R.id.fragment_holder, RepoDetailFragment.newInstance(repoList[adapterPosition]))
                transaction.addToBackStack(null).commit()
            }
        }
    }
}