package pl.kaemo.recipefinder.ui.favouritesActivity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import pl.kaemo.recipefinder.R
import pl.kaemo.recipefinder.ui.favouritesActivity.ui.theme.RecipeFinderTheme

var isEmpty: Boolean = false
val recipes: List<Recipe> = SampleData.recipesListSample

@AndroidEntryPoint
class FavouritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Layout() }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun Test() {
    Layout()
}

@Composable
fun Layout() {
    RecipeFinderTheme {
        Background()
        if (isEmpty) EmptyState()
        Column(modifier = Modifier.padding(5.dp)) {
            NavBar()
            LazyColumn {
                items(recipes) { recipe ->
                    RecipePreviewCard(recipe)
                }
            }
        }
    }
}

@Composable
fun Background() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    )
}

data class Recipe(val title: String, val infoText: String, val missingIngredients: String)

@Composable
fun RecipePreviewCard(msg: Recipe) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.surface,
        elevation = 3.dp,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
    ) {
        var isExpanded by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .padding(all = 10.dp)
                .clickable { isExpanded = !isExpanded }
                .animateContentSize()
        ) {
            Image(
                painter = painterResource(R.drawable._37684_636x393),
                contentDescription = "Recipe picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(3.dp))
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = msg.title,
                    color = MaterialTheme.colors.primaryVariant,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = msg.infoText,
                    color = MaterialTheme.colors.primaryVariant,
                    style = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = msg.missingIngredients,
                    color = MaterialTheme.colors.primaryVariant,
                    style = MaterialTheme.typography.body2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 4
                )
            }
        }
    }
}

@Composable
fun NavBar() {
    Surface(color = MaterialTheme.colors.background) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Favourites",
                    color = MaterialTheme.colors.primaryVariant,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Image(
                    painter = painterResource(R.drawable.ic_baseline_info_24),
                    contentDescription = "Info tool",
                    modifier = Modifier
                        .padding(5.dp)
                        .size(17.dp)
                        .clickable { },
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primaryVariant)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!isEmpty) {
                    Image(
                        painter = painterResource(R.drawable.ic_baseline_sort_24),
                        contentDescription = "Sort button",
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primaryVariant),
                        modifier = Modifier
                            .size(31.dp)
                            .clickable { }
                    )
                }
                Image(
                    painter = painterResource(R.drawable.ic_baseline_more_vert_24),
                    contentDescription = "More button",
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .padding(start = 10.dp)
                        .size(31.dp)
                        .clickable { },
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primaryVariant)
                )
            }
        }
    }
}

@Composable
fun EmptyState() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.empty_state_no_favourites),
            contentDescription = "Empty state picture",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "No favourites recipes found",
            color = MaterialTheme.colors.primaryVariant,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        Text(
            text = "To add new favourite recipe just click on the heart icon next to the recipe title",
            color = MaterialTheme.colors.primaryVariant,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center
        )
    }
}
