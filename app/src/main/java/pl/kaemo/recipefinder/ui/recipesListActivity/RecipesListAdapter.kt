package pl.kaemo.recipefinder.ui.recipesListActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.kaemo.recipefinder.R

class RecipesListAdapter : RecyclerView.Adapter<RecipesListAdapter.RecipesListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecipesListViewHolder {
        return RecipesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_recipes_list_recyclerview_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return FakeDataBase.recipeTitle.size
    }

    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        val cardImage: ImageView =
            holder.itemView.findViewById(R.id.activity_recipes_list_recyclerview_card_image_view)
        val cardTitle: TextView =
            holder.itemView.findViewById(R.id.activity_recipes_list_recyclerview_card_ingredient_title)
        val cardSummary: TextView =
            holder.itemView.findViewById(R.id.activity_recipes_list_recyclerview_card_ingredient_summary)

        cardTitle.text = FakeDataBase.recipeTitle[position]
        cardSummary.text = FakeDataBase.recipeSummary[position]

        Glide.with(holder.view.context)
            .load(FakeDataBase.imageUlr[position])
            .into(cardImage)

        holder.itemView.findViewById<View>(R.id.activity_recipes_list_recyclerview_card_view)
            .setOnClickListener {
                Toast.makeText(
                    holder.view.context,
                    "[id: $position] not yet implemented",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    class RecipesListViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}

