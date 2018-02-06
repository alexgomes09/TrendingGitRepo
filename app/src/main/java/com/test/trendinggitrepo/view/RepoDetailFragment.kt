package com.test.trendinggitrepo.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.test.trendinggitrepo.R
import com.test.trendinggitrepo.model.RepoResponse
import com.test.trendinggitrepo.util.AppUtil
import kotlinx.android.synthetic.main.fragment_detail_view.*

/**
 * Created by alexgomes on 2018-02-05.
 */
class RepoDetailFragment : Fragment() {

    companion object {
        private val PARAM = "repo"
        private lateinit var selectedRepo: RepoResponse.Repo

        fun newInstance(param: RepoResponse.Repo): RepoDetailFragment {
            val args: Bundle = Bundle()
            args.putParcelable(PARAM, param)

            val fragment = RepoDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        selectedRepo = arguments.getParcelable(PARAM)
        return inflater.inflate(R.layout.fragment_detail_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = selectedRepo.name

        Glide.with(activity)
                .load(selectedRepo.owner.avatarUrl)
                .placeholder(R.mipmap.ic_launcher)
                .skipMemoryCache(true)
                .dontAnimate().into(repo_image_detail)

        repo_name.text = selectedRepo.name
        repo_full_name.text = selectedRepo.fullName
        repo_stars_detail.text = selectedRepo.stars.toString()
        repo_forks_detail.text = selectedRepo.forks.toString()
        repo_issues.text = selectedRepo.openIssues.toString()
        repo_created.text = AppUtil.makeTimeStampReadable(selectedRepo.createdAt)
        repo_updated.text = AppUtil.makeTimeStampReadable(selectedRepo.updatedAt)
        repo_description_detail.text = selectedRepo.description

        external_link.setOnClickListener({
            if (!TextUtils.isEmpty(selectedRepo.repoLink)) {
                AppUtil.openUrl(activity, selectedRepo.repoLink)
            } else {
                Toast.makeText(activity, "Invalid link found", Toast.LENGTH_LONG).show()
            }
        })
    }
}