package pl.kaemo.recipefinder.ui.favouritesActivity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

var isEmpty: Boolean = false //todo
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
        Surface(color = MaterialTheme.colors.background) {
            var searchButtonClicked by rememberSaveable { mutableStateOf(false) }
            if (isEmpty) EmptyState()
            Column(modifier = Modifier.padding(5.dp)) {
                NavBar(onSearchButtonClicked = { searchButtonClicked = !searchButtonClicked })
                if (!isEmpty && searchButtonClicked) SearchBar()
                LazyColumn {
                    items(recipes) { recipe ->
                        RecipePreviewCard(recipe)
                    }
                }
            }
        }
    }
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
        var isExpanded by rememberSaveable { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .padding(all = 10.dp)
                .animateContentSize()
                .size(if (isExpanded) Int.MAX_VALUE.dp else 130.dp)
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
                )
            }
        }
    }
}

@Composable
fun NavBar(onSearchButtonClicked: () -> Unit) {
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
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Information",
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .size(17.dp)
                    .clickable { }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (!isEmpty) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search for recipe",
                    tint = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .size(31.dp)
                        .clickable { onSearchButtonClicked() }
//                        .animateContentSize() //poprawić - nie działa / animatedVisibility ?
                )
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    imageVector = Icons.Filled.Sort,
                    contentDescription = "Sort recipes",
                    tint = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .size(31.dp)
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.width(9.dp))
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More options",
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .size(31.dp)
                    .clickable { }
            )
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .heightIn(min = 55.dp)
            .fillMaxWidth()
            .padding(5.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = MaterialTheme.colors.primaryVariant
            )
        },
        placeholder = {
            Text("Search for recipe")
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp)
    )
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

