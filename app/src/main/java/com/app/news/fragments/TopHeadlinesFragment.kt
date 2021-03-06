package com.app.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.news.MainActivity
import com.app.news.MainActivity.FragmentRefreshListener
import com.app.news.R
import com.app.news.adapter.NewsDataListAdapter
import com.app.news.databinding.FragmentNewsDataBinding
import com.app.news.utility.NewsAlertDialog
import com.app.news.viewmodel.TopHeadlinesViewModel


class TopHeadlinesFragment : Fragment(), MainActivity.FragmentRefreshListener {

    lateinit var binding : FragmentNewsDataBinding;
    lateinit var adapter : NewsDataListAdapter
    lateinit var topHeadlinesViewModel : TopHeadlinesViewModel
    var newsAlertDialog = NewsAlertDialog()

    companion object{
        const val TAG : String = "TopHeadlinesFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_data, container, false)
        topHeadlinesViewModel = ViewModelProvider(this).get(TopHeadlinesViewModel::class.java)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Top Headlines")

        activity?.let { newsAlertDialog.showAlertDialog(
            activity as AppCompatActivity,
            "Top Headlines",
            "Loading... Please wait"
        ) }
        topHeadlinesViewModel.fetchHeadlines()

        adapter = NewsDataListAdapter()
        val recyclerView: RecyclerView = binding.topHeadlinesRV
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        topHeadlinesViewModel.headlinesLiveData.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "Headlines Count: " + it)
            newsAlertDialog.dismissAlertDialog()
            adapter.updateList(it.articles)
            adapter.notifyDataSetChanged()
        })

        topHeadlinesViewModel.response.observe(viewLifecycleOwner, Observer {
            newsAlertDialog.dismissAlertDialog()
            Log.d(TAG, it)
        })

        (activity as MainActivity?)?.setRefreshListener(this)
        return binding.root
    }

    override fun refreshFragment(searchItem: String) {
        Toast.makeText(activity, "searchItem : $searchItem", Toast.LENGTH_LONG).show();
        activity?.let { newsAlertDialog.showAlertDialog(
            activity as AppCompatActivity,
            "",
            "Searching... Please wait"
        ) }
        topHeadlinesViewModel.fetchNewsForSearch(searchItem)
    }
}