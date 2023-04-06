package com.example.amphibians.ui.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.AmphibiansData


@Composable
fun HomeAmphibians(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
//State view model UI
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier)
        is AmphibiansUiState.Success -> AmphibiansListScreen(
            amphibians = amphibiansUiState.amphibiansData,
            modifier
        )
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier)

    }

}

@Composable
private fun AmphibiansListScreen(amphibians: List<AmphibiansData>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = amphibians,
            key = { amphibian ->
                amphibian.name
            }
        ) { amphibian ->
            CardAmphibians(amphibiansData = amphibian)
        }
    }
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
                name = amphibiansData.name,
                type = amphibiansData.type,
                desc = amphibiansData.description,

                )
            ImageAmphibians(amphibiansData = amphibiansData)
        }

    }
}

@Composable
fun DescAmphibians(
    desc: String,
    name: String,
    type: String,
    modifier: Modifier = Modifier
) {
//имя и описание амфибии
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(name, modifier = modifier.padding(16.dp))
        Text(type, modifier = modifier.padding(16.dp))
        Text(
            desc,
            modifier = modifier.padding(end = 16.dp, top = 8.dp, start = 16.dp, bottom = 16.dp)
        )

    }
}

@Composable
fun ImageAmphibians(
    modifier: Modifier = Modifier,
    amphibiansData: AmphibiansData,
) {


//Изображение
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(amphibiansData.imgSrc)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        error = (painterResource(id = R.drawable.ic_error)),
        placeholder = (painterResource(id = R.drawable.ic_loading)),

        modifier = modifier
            .fillMaxWidth()


    )
}


@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    animationDuration: Int = 1000,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        )
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .rotate(degrees = rotateAnimation),
            painter = painterResource(R.drawable.ic_loading),
            contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CardAmphibiansPreview() {

    CardAmphibians(amphibiansData = AmphibiansData("sasdas", "sadasdasd", "asdasdasda", "sadsda"))
}

@Preview(showBackground = true)
@Composable
fun ErrorAmphibiansPreview() {

    ErrorScreen(retryAction = { /*TODO*/ })
}

@Preview(showBackground = true)
@Composable
fun LoadAmphibiansPreview() {

    LoadingScreen()
}


