package pe.codex.musicspot.ui

import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.rounded.PlayCircle
import androidx.compose.material.icons.rounded.SkipNext
import androidx.compose.material.icons.rounded.SkipPrevious
import androidx.compose.material.icons.rounded.StopCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pe.codex.musicspot.R
import pe.codex.musicspot.ui.music_reproducer.MusicReproducerUiState
import pe.codex.musicspot.ui.music_reproducer.MusicState
import pe.codex.musicspot.ui.music_reproducer.ReproducerViewModel
import pe.codex.musicspot.ui.theme.Medium
import pe.codex.musicspot.ui.theme.MusicSpotTheme
import pe.codex.musicspot.ui.theme.MusicTheme
import pe.codex.musicspot.ui.theme.Small
import pe.codex.musicspot.ui.theme.Small_2

@Composable
fun ProducerFragment(
    modifier: Modifier = Modifier,
    producerViewModel: ReproducerViewModel = hiltViewModel()
) {
    val uiState by producerViewModel.uiState.collectAsStateWithLifecycle()
    val musicState by producerViewModel.musicState.collectAsStateWithLifecycle()
    ProducerScreen(
        modifier = modifier,
        uiState = uiState,
        musicState = musicState,
        onPlayPauseClick = producerViewModel::onPlayPauseClick
    )
}

@Composable
fun ProducerScreen(
    modifier: Modifier = Modifier,
    uiState: MusicReproducerUiState,
    onPlayPauseClick: (Boolean) -> Unit,
    musicState: MusicState
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.img_adele_poster),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .drawWithCache {
                    val diagonalGradient = Brush.linearGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        start = Offset(size.width, size.height / 6),
                        end = Offset(0f, 0f)
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(diagonalGradient, blendMode = BlendMode.Multiply)
                    }
                },
            alignment = Alignment.TopEnd
        )
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopBarProducer()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ReproductionDetails(
                    musicTitle = uiState.musicName,
                    artistName = uiState.artistName,
                    onSingerModeClick = {},
                    onFavouriteClick = {},
                    music = uiState.currentSong,
                    currentPosition = musicState.currentPosition
                )
                ControlsBar(
                    onPlayPauseClick = { uiState.currentSong?.isPlaying?.let(onPlayPauseClick) },
                    onSkipNextClick = {},
                    onSkipPreviousClick = {},
                    onAleatoryClick = {},
                    onRepeatModeClick = {},
                    isPlaying = uiState.isPlaying
                )
            }
        }

    }
}

@Composable
fun ReproductionDetails(
    modifier: Modifier = Modifier,
    musicTitle: String,
    artistName: String,
    onSingerModeClick: () -> Unit,
    onFavouriteClick: () -> Unit,
    music: MediaPlayer?,
    currentPosition: Int
) {
    val duration: String = music?.duration?.toMinutesAndSeconds() ?: "0:00"
    var size by remember {
        mutableStateOf(0f)
    }


    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier) {
                Text(text = musicTitle, fontWeight = FontWeight.SemiBold, fontSize = Medium)
                Text(text = artistName, color = MusicTheme.colors.secondary, fontSize = Small_2)
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                imageVector = Icons.Filled.Mic,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.White),
                modifier = Modifier
                    .width(24.dp)
                    .rotate(45f)
                    .clickable { onSingerModeClick() }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                imageVector = Icons.Filled.HeartBroken,
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = MusicTheme.colors.primary),
                modifier = Modifier
                    .width(24.dp)
                    .clickable { onFavouriteClick() }
            )
        }
        Column(modifier = Modifier.padding(vertical = 14.dp)) {
            Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                            .fillMaxWidth(size)
                            .clip(RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
                            .background(color = MusicTheme.colors.primary)
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(size)
                            .blur(12.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
                        thickness = 6.dp,
                        color = MusicTheme.colors.primary
                    )
                }
                Divider(
                    modifier = Modifier
                        .size(16.dp)
                        .offset(x = (-2).dp)
                        .border(
                            border = BorderStroke(
                                width = 4.dp,
                                color = MusicTheme.colors.primary
                            ),
                            shape = CircleShape
                        )
                        .blur(12.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded),
                    color = MusicTheme.colors.primary
                )
                Spacer(
                    modifier = Modifier
                        .height(3.dp)
                        .fillMaxWidth()
                        .offset(x = (-2.25).dp)
                        .clip(RoundedCornerShape(topEnd = 4.dp, bottomEnd = 4.dp))
                        .background(color = MusicTheme.colors.secondary)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = currentPosition.toMinutesAndSeconds().also {
                        size = currentPosition.toFloat() / (music?.duration?.toFloat() ?: 0f)
                    },
                    color = MusicTheme.colors.secondary,
                    fontWeight = FontWeight.Light,
                    fontSize = Small
                )
                Text(
                    text = duration,
                    color = MusicTheme.colors.secondary,
                    fontWeight = FontWeight.Light,
                    fontSize = Small
                )
            }
        }
    }
}

@Composable
fun ControlsBar(
    modifier: Modifier = Modifier,
    onPlayPauseClick: () -> Unit,
    onSkipNextClick: () -> Unit,
    onSkipPreviousClick: () -> Unit,
    onAleatoryClick: () -> Unit,
    onRepeatModeClick: () -> Unit,
    isPlaying: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_aleatory),
            contentDescription = "Aleatory",
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.clickable { onAleatoryClick() }
        )
        Image(
            imageVector = Icons.Rounded.SkipPrevious, contentDescription = "Previous",
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .height(52.dp)
                .clickable { onSkipPreviousClick() },
            contentScale = ContentScale.FillHeight,
        )
        Image(
            imageVector = if (isPlaying) Icons.Rounded.StopCircle else Icons.Rounded.PlayCircle,
            contentDescription = "Play",
            colorFilter = ColorFilter.tint(color = MusicTheme.colors.primary),
            modifier = Modifier
                .height(96.dp)
                .clickable { onPlayPauseClick() },
            contentScale = ContentScale.FillHeight
        )
        Image(
            imageVector = Icons.Rounded.SkipNext, contentDescription = "Next",
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .height(52.dp)
                .clickable { onSkipNextClick() },
            contentScale = ContentScale.FillHeight
        )
        Image(
            painter = painterResource(id = R.drawable.ic_repeat),
            contentDescription = "Aleatory",
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.clickable { onRepeatModeClick() }
        )
    }
}

fun Int.toMinutesAndSeconds(): String {
    val minutes = this / 1000 / 60
    val seconds = (this / 1000) % 60
    return "$minutes:$seconds"
}

@Composable
fun TopBarProducer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_dropdown),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MusicTheme.colors.secondary)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.width(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_menu_options),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier.width(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProducerScreenPreview() {
    MusicSpotTheme {
        ProducerScreen(
            uiState = MusicReproducerUiState(
                currentSong = null,
                musicName = "Caroline Hull",
                artistName = "Jimmie Avila",
                isPlaying = false,
            ),
            onPlayPauseClick = {},
            musicState = MusicState()
        )
    }
}