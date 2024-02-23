package com.example.a30daysofrecipes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysofrecipes.data.Recipe
import com.example.a30daysofrecipes.data.RecipeRepository
import com.example.a30daysofrecipes.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    RecipeApp(RecipeRepository.Recipes)
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                Image(
                    painter = painterResource(recipe.imageRes),
                    contentDescription = stringResource(id = recipe.titleRes),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),

                    contentScale = ContentScale.Crop
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(100.dp)
                        .height(40.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.tertiary)

                ) {
                    Text(
                        text = stringResource(id = recipe.dayNumberRes),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Text(
                text = stringResource(id = recipe.titleRes),
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
                    .padding(start = 8.dp)
            )

            OpenUrlButton(
                stringResource(id = recipe.urlRes),
                modifier = Modifier
                    .offset(x = 8.dp)
            )
        }
    }
}

@Composable
fun RecipeApp(recipes: List<Recipe>) {
    LazyColumn(
    )
        {
            items(recipes) {
                RecipeCard(recipe = it)
            }
        }
}

@Composable
fun OpenUrlButton(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        ),
        onClick = {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }, modifier = modifier) {
        Text("View Method")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        RecipeCard(recipe = RecipeRepository.Recipes[0])
    }
}