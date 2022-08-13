package pl.kaemo.recipefinder.ui.mainActivity.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.kaemo.recipefinder.R

class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MainViewHolder {
        Log.d("TAG", "onCreateViewHolder")
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_activity_recyclerview_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return FakeDataBase.ingredientsList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ingredientName: TextView =
            holder.itemView.findViewById(R.id.main_activity_recyclerview_textView)
        ingredientName.text = FakeDataBase.ingredientsList[position]

        holder.itemView.findViewById<ImageButton>(R.id.main_activity_recyclerview_imageButton_remove)
            .setOnClickListener {
                deleteItem(holder.bindingAdapterPosition) //jak było poprostu "position" to coś dziwnego się dzialo z indeksowaniem w notifyItemRemoved(position) i się apka crashowała - why?
                //Thread.sleep(500)  //apka crshuje się gdy za szybko jest usuwanie klikane. Jest jakiś sposbób żeby zapobiec crashom? Jak ustawić żeby apka czekała aż animacja usuwania się zakończy? sleep nie pomaga
            }

    }

    class MainViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)

    private fun deleteItem(position: Int) {
        FakeDataBase.ingredientsList.removeAt(position)
        notifyItemRemoved(position)
    }
}