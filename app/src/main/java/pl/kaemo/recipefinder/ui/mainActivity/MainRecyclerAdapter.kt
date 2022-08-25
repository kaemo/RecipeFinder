package pl.kaemo.recipefinder.ui.mainActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.util.AndroidLogger
import pl.kaemo.recipefinder.ui.util.LogcatLogger

class MainRecyclerAdapter(val onDeleted: (Int) -> Unit) :
    RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    private val logger: LogcatLogger = AndroidLogger("TAG") // lub FileLogger()

    private var ingredientsList: List<String> = listOf()

    fun update(list: List<String>) {
        ingredientsList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MainViewHolder {
        logger.logMessage("Adapter: onCreateViewHolder")
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ingredientName: TextView =
            holder.itemView.findViewById(R.id.main_activity_recyclerview_textView)
        ingredientName.text = ingredientsList[position]

        holder.itemView.findViewById<ImageButton>(R.id.activity_recipes_list_recyclerview_imageButton_remove)
            .setOnClickListener {
                onDeleted(holder.bindingAdapterPosition)
            }

    }

    class MainViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)
}