package com.route.newsapp.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.route.newsapp.R
import com.route.newsapp.ui.Category

class categoriesFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }
    val categories= listOf(
        Category(
            "sports", R.drawable.ball,
            R.string.sports, R.color.red
        ),
        Category(
            "technology", R.drawable.environment,
            R.string.technology, R.color.blue
        ),
        Category(
            "health", R.drawable.health,
            R.string.health, R.color.pink
        ),
        Category(
            "business", R.drawable.bussines,
            R.string.business, R.color.brown_orange
        ),
        Category(
            "general", R.drawable.environment,
            R.string.general, R.color.baby_blue
        ),
        Category(
            "science", R.drawable.science,
            R.string.science, R.color.yellow
        ),
    )

lateinit var recyclerView:RecyclerView
val Adapter= CategoriesAdapter(categories)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }
    var onCategoryClickListner: OnCategoryClickListner?=null
    interface OnCategoryClickListner{
     fun   onCategoryClick(category: Category)
    }

    private fun initRecyclerView() {
        recyclerView=requireView().findViewById(R.id.recycler_view)
        recyclerView.adapter=Adapter
        Adapter.onItemClickListner=object : CategoriesAdapter.OnItemClickListner {
            override fun onItemClick(pos: Int, item: Category) {
               onCategoryClickListner?.onCategoryClick(category = item)
            }

        }
    }
}