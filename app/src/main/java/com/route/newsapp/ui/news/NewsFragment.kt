package com.route.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.route.newsapp.R
import com.route.newsapp.databinding.FragmentNewsBinding
import com.route.newsapp.model.SourcesItem
import com.route.newsapp.ui.Category


class NewsFragment : Fragment() {

    companion object {
        fun getInstance(category: Category): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    lateinit var category: Category
    lateinit var viewDataBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       viewDataBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false)
        return viewDataBinding.root
    }


    lateinit var viewModel: NewsViewModel
    val adapter = NewsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.recyclerViewNews.adapter = adapter
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        subscribeToLiveData()
        viewModel.getNewsSources(category)
    }

    private fun subscribeToLiveData() {
        viewModel.progressBarVisible.observe(viewLifecycleOwner, Observer { isVisible ->
            viewDataBinding.progressBar.isVisible = isVisible

        })
        viewModel.sourcesLiveData.observe(viewLifecycleOwner, Observer { data ->
            showTabs(data)
        })
        viewModel.newsList.observe(viewLifecycleOwner, Observer { data ->
            adapter.changeData(data)
        })
        viewModel.messageLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(
                        requireContext(), it,
                        Toast.LENGTH_LONG
                    ).show()
        })
    }




    private fun showTabs(sources: List<SourcesItem?>?) {
        sources?.forEach { item ->
            val tab = viewDataBinding.tablayout.newTab()
            tab.tag = item
            tab.text = item?.name
            viewDataBinding.tablayout.addTab(tab)
        }
        viewDataBinding.tablayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // val source =  sources?.get(tab?.position?:0)
                    val source = tab?.tag as SourcesItem
                    viewModel.loadNews(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    viewModel.loadNews(source)
                }
            }
        )
        viewDataBinding.tablayout.getTabAt(0)?.select()

    }


}





