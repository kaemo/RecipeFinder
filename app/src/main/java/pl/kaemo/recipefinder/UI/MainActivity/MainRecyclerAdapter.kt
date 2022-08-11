package pl.kaemo.recipefinder.UI.MainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private var ingirdients = arrayOf("eggs", "milk", "chicken", "cheese")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_activity_recyclerview_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemName.text = ingirdients[position]
    }

    override fun getItemCount(): Int {
        return ingirdients.size
    }

    class MainViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.main_activity_recyclerview_textView)
    }
}