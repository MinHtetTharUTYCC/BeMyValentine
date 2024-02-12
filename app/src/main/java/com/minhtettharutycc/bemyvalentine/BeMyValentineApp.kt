package com.minhtettharutycc.bemyvalentine

import android.os.Build.VERSION.SDK_INT
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder


import com.minhtettharutycc.bemyvalentine.ui.theme.BeMyValentineTheme
import com.minhtettharutycc.bemyvalentine.ui.theme.fontFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeMyValentineApp() {

    val name = "Ou Ou Lay"
    val propose = stringResource(id = R.string.will_you_be, "Dear $name")
    var currentIndex by remember { mutableStateOf(0) }
    val propose_each_word = propose.split(" ")
    var currentPropose by remember { mutableStateOf("") }

//    val nosList = listOf(
//        NoAndPhoto(R.drawable.one_1, "No"),
//        NoAndPhoto(R.drawable.two_2, "Are you sure?"),
//        NoAndPhoto(R.drawable.three_3, "Really sure?"),
//        NoAndPhoto(R.drawable.four_4, "Think again!"),
//        NoAndPhoto(R.drawable.five_5, "Last chance!"),
//        NoAndPhoto(R.drawable.six_6, "Surely not!"),
//        NoAndPhoto(R.drawable.seven_7, "You might regret this!"),
//        NoAndPhoto(R.drawable.eight_8, "Give it another thought!"),
//        NoAndPhoto(R.drawable.nine_9, "Are you absolutely certain?"),
//        NoAndPhoto(R.drawable.ten_10, "This could be a mistake!"),
//        NoAndPhoto(R.drawable.eleven_11, "Have a heart!"),
//        NoAndPhoto(R.drawable.twelve_12, "Change of heart?"),
//        NoAndPhoto(R.drawable.thirteen_13, "Wouldn't you consider?"),
//        NoAndPhoto(R.drawable.fourteen_14, "Is that your final answer?"),
//        NoAndPhoto(R.drawable.fifteen_15, "You are breaking my heart ;(")
//    )

    val nosList = listOf(
        NoAndPhoto(R.drawable.one_1, "No"),
        NoAndPhoto(R.drawable.two_2, "Are you sure?"),
        NoAndPhoto(R.drawable.three_3, "Really sure?"),
        NoAndPhoto(R.drawable.four_4, "Think again!"),
        NoAndPhoto(R.drawable.five_5, "Last chance!"),
    )
    var currentNoIndex by remember { mutableStateOf(0) }


    var isReject by remember { mutableStateOf(false) }
    var yesSize by remember { mutableStateOf(40) }
    var yesFont by remember { mutableStateOf(15) }
    var isEndOfNo by remember { mutableStateOf(false) }
    var isFinal by remember { mutableStateOf(false) }
    var isAccepted by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
        },

        ) { paddingValues ->
        var currentPhotoId = if (isAccepted) R.drawable.last else if (isEndOfNo) R.drawable.before_last else nosList[currentNoIndex].photoResId
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = currentPhotoId),
                    contentDescription = stringResource(R.string.our_bagan),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToScale(0.5f, 0.5f, 0.5f, 1f)
                    })
                )
            }
        }

        var gifId = when (currentNoIndex) {
            4-> if(!isEndOfNo) R.drawable.i_am_fine else R.drawable.meow_dancing

            else -> { R.drawable.meow_dancing }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), contentAlignment = Alignment.TopCenter) {
            Card(
                modifier = Modifier.size(200.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                if(isAccepted){
                    gifId = R.drawable.meow_kiss
                }
                GifImage(gifId)
            }
        }

        /*
        if (isEndOfNo) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Card(
                    modifier = Modifier.size(200.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.haha),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }

            }

        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                nosList[currentNoIndex]?.photoResId?.let { photoResId ->
                    Card(
                        modifier = Modifier.size(200.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        painterResource(id = photoResId)?.let { painter ->
                            GifImage(drawableRes = R.drawable.tears_cry)
                        }
                    }
                }

            }
        }
         */

        if (isAccepted) {   //Congratulations
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(200.dp))
                Text(
                    text = stringResource(R.string.see_you),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge)
//                DisplayGif()

            }

        } else {

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = currentPropose,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
                if (currentIndex <= propose_each_word.size - 1) {
                    LaunchedEffect(currentIndex) {
                        currentPropose += (propose_each_word.getOrNull(currentIndex) ?: "") + " "
                        delay(250)
                        currentIndex++
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.paddding_medium)))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.paddding_medium)),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    fun accepted() {
                        isAccepted = true
                        currentPhotoId = R.drawable.last
                    }
                    Button(
                        onClick = { accepted() },
                        modifier = Modifier
                            .weight(1f)
                            .height(yesSize.dp)
                    ) {
                        Text("Yes", fontFamily = fontFamily, fontSize = yesFont.sp, textAlign = TextAlign.Center)
                    }


                    fun reject() {
                        yesSize += 5
                        yesFont += 2
                        currentNoIndex++

                    }
                    if (!isEndOfNo) {
                        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.paddding_medium)))
                        Button(
                            onClick = {
                                if (currentNoIndex < nosList.size - 1) {
                                    reject()
                                } else {
                                    isEndOfNo = true
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(nosList[currentNoIndex].no, fontFamily = fontFamily, fontSize = 15.sp, textAlign = TextAlign.Center)
                        }
                    }
                    else {
                        isFinal = true
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))

            }

        }


    }
}

data class NoAndPhoto(val photoResId: Int, val no: String)

@Composable
fun GifImage(@DrawableRes drawableRes: Int) {
    val imageLoader = coil.ImageLoader.Builder(LocalContext.current)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            model = drawableRes,
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}


@Preview
@Composable
fun BeMyValentineAppPreview() {
    BeMyValentineTheme {
        BeMyValentineApp()
    }
}