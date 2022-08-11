package pl.kaemo.recipefinder.UI.MainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private var items = arrayOf("eggs", "milk", "chicken", "cheese")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerAdapter.MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_activity_recyclerview_card, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> {
                holder.itemName.text = items[position]
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class MainViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        var itemName: TextView = itemView.findViewById(R.id.main_activity_recyclerview_textView)
    }
}