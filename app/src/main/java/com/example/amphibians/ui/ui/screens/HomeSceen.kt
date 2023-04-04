package com.example.amphibians.ui.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.R
import com.example.amphibians.model.AmphibiansData


@Composable
fun HomeAmphibians() {
//State view model UI
}

@Composable
fun CardAmphibians(amphibiansData: AmphibiansData, modifier: Modifier = Modifier) {
    //Card UI
    Card(
        elevation = 4.dp,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            DescAmphibians(
                desc = amphibiansData.description,
                name = amphibiansData.name,
                type = amphibiansData.type
            )
            ImageAmphibians()
        }

    }
}

@Composable
fun DescAmphibians(
    desc: String,
    name: String,
    type: String
) {
//имя и описание амфибии
    Column {
        Text(desc)
        Text(name)
        Text(type)
    }
}

@Composable
fun ImageAmphibians(modifier: Modifier = Modifier) {
//Изображение
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background) ,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
        )
}

@Preview(showBackground = true)
@Composable
fun CardAmphibiansPreview() {

    CardAmphibians(amphibiansData = AmphibiansData("sasdas", "sadasdasd", "asdasdasda", "sadsda"))
}

