package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Recipe(val name: String, val imageRes: Int)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeApp()
        }
    }
}

@Composable
fun RecipeApp() {
    var recipeName by remember { mutableStateOf(TextFieldValue("")) }
    val recipeList = remember { mutableStateListOf<Recipe>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Inputs
        TextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón Agregar
        Button(
            onClick = {
                if (recipeName.text.isNotEmpty()) {
                    // Se usa una imagen local para cada receta.
                    recipeList.add(Recipe(recipeName.text, R.drawable.angry))
                    recipeName = TextFieldValue("")  // Limpiar el nombre
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de recetas
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(recipeList.size) { index ->
                RecipeItem(recipe = recipeList[index])
            }
        }
    }
}

@Composable
fun TextField(
    value: TextFieldValue,
    onValueChange: () -> Unit,
    label: () -> Unit,
    modifier: Modifier
) {
    TODO("Not yet implemented")
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Row(modifier = Modifier.padding(8.dp)) {
        // Usamos una imagen local, aquí se debe especificar un recurso de imagen existente.
        val painter: Painter = painterResource(id = recipe.imageRes)
        Image(
            painter = painter,
            contentDescription = "Imagen de ${recipe.name}",
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Text(recipe.name, style = MaterialTheme.typography.h6)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RecipeApp()
}
