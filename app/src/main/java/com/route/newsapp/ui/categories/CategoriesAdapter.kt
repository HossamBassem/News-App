package com.route.newsapp.ui.categories

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.route.newsapp.R
import com.route.newsapp.ui.Category

class CategoriesAdapter(val categories:List<Category>) :RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(
                if (viewType == isLeftSided)
                    R.layout.item_category_left_sided
                else R.layout.item_category_right_sided,

                parent, false)
        return ViewHolder(view)
    }
    val isRightSided = 10;
    val isLeftSided = 20;
    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) return isLeftSided;
        return isRightSided;

    }
    var onItemClickListner: OnItemClickListner?=null
    interface OnItemClickListner{

        fun onItemClick(pos:Int,item: Category)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = categories[position]
        holder.title.setText(cat.titleId)
        holder.image.setImageResource(cat.imageId)
        holder.parent.setCardBackgroundColor(
            holder.parent.context?.getColor(cat.backgroundColor) ?: 0
        )
            holder.itemView.setOnClickListener {
                onItemClickListner?.onItemClick(position,cat)
            }
        }

    override fun getItemCount(): Int {
        return categories.size
    }




    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parent: MaterialCardView = itemView.findViewById(R.id.parent)
        val child: ConstraintLayout = itemView.findViewById(R.id.child)
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}