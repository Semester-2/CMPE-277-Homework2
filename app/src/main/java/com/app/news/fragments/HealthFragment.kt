package com.app.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.news.R
import com.app.news.adapter.NewsDataListAdapter
import com.app.news.databinding.FragmentNewsDataBinding
import com.app.news.model.Category
import com.app.news.utility.NewsAlertDialog
import com.app.news.viewmodel.TopHeadlinesViewModel

class HealthFragment : Fragment() {

    lateinit var binding : FragmentNewsDataBinding;
    lateinit var adapter : NewsDataListAdapter
    lateinit var topHeadlinesViewModel : TopHeadlinesViewModel
    var newsAlertDialog = NewsAlertDialog()

    companion object{
        const val TAG : String = "HealthFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_data, container, false)
        topHeadlinesViewModel = ViewModelProvider(this).get(TopHeadlinesViewModel::class.java)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setTitle("Health Headlines")

        activity?.let { newsAlertDialog.showAlertDialog(activity as AppCompatActivity,"Health Headlines" , "Loading... Please wait") }
        topHeadlinesViewModel.fetchNewsForCategory(Category.HEALTH)

        adapter = NewsDataListAdapter()
        val recyclerView: RecyclerView = binding.topHeadlinesRV
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        topHeadlinesViewModel.headlinesLiveData.observe(viewLifecycleOwner, Observer{
            Log.d(TAG, "Headlines Count: " + it)
            newsAlertDialog.dismissAlertDialog()
            adapter.updateList(it.articles)
            adapter.notifyDataSetChanged()
        } )
        return binding.root
    }
}