package pl.kaemo.recipefinder.ui.mainActivity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class MainRecyclerAdapter(val onDeleted: (Int) -> Unit) : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private var ingredientsList: List<String> = listOf()

    fun update(list: List<String>) {
        ingredientsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MainViewHolder {
        Log.d("TAG", "Adapter: onCreateViewHolder")
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_activity_recyclerview_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ingredientName: TextView =
            holder.itemView.findViewById(R.id.main_activity_recyclerview_textView)
        ingredientName.text = ingredientsList[position]

        holder.itemView.findViewById<ImageButton>(R.id.main_activity_recyclerview_imageButton_remove)
            .setOnClickListener {
                onDeleted(holder.bindingAdapterPosition)
            }

    }

    class MainViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)
}