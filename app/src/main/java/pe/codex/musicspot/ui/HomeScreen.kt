package pe.codex.musicspot.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pe.codex.musicspot.R
import pe.codex.musicspot.ui.theme.HorizontalPadding
import pe.codex.musicspot.ui.theme.MusicSpotTheme
import pe.codex.musicspot.ui.theme.MusicTheme
import pe.codex.musicspot.ui.theme.Small
import pe.codex.musicspot.ui.theme.TitleScreenStyle

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MusicTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
        TopHomeScreenBar(modifier = Modifier.padding(vertical = 12.dp))
        HorizontalScrollableSectionWithTitle(
            modifier = Modifier.padding(top = 24.dp),
            titleSection = "Your favorite artist"
        ) {
            items(items = artistData, key = { item -> item.idArtist }) {
                RoundedImageWithDescription(item = it)
            }
        }

        HorizontalScrollableSectionWithTitle(
            modifier = Modifier.padding(top = 18.dp),
            titleSection = "Recent played"
        ) {
            items(items = recentPlayedMocked, key = { item -> item.idMusic }) {
                SquareCardWithDescription(imageLink = it.imageUrl, description = it.songName)
            }
        }

        HorizontalScrollableSectionWithTitle(
            modifier = Modifier.padding(top = 18.dp),
            titleSection = "Made for you"
        ) {
            items(items = dailyRecommendationData, key = { item -> item.idRecommendation }) {
                VerticalRectangleRounded(imageLink = it.imageUrl)
            }
        }
    }

}


@Composable
fun SquareCardWithDescription(
    modifier: Modifier = Modifier,
    imageLink: String,
    description: String
) {
    Column(modifier = modifier.width(128.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(imageLink)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            textAlign = TextAlign.Center,
            fontSize = Small,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}



@Composable
fun VerticalRectangleRounded(imageLink: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(imageLink)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = Modifier
            .width(128.dp)
            .aspectRatio(0.68f)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.FillHeight
    )
}

@Composable
fun HorizontalScrollableSectionWithTitle(
    modifier: Modifier = Modifier,
    titleSection: String,
    item: LazyListScope.() -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = HorizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = titleSection, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Image(
                painter = painterResource(id = R.drawable.ic_angle_right),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.White)
            )
        }
        LazyRow(
            modifier = Modifier.padding(vertical = 24.dp),
            contentPadding = PaddingValues(start = HorizontalPadding),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item()
        }
    }
}

@Composable
fun RoundedImageWithDescription(modifier: Modifier = Modifier, item: ArtistData) {
    Column(modifier = modifier.width(84.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.name,
            textAlign = TextAlign.Center,
            fontSize = Small,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 6.dp),
            maxLines = 1
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHomeScreenBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = HorizontalPadding), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.88f)) {
            Text(text = buildAnnotatedString {
                withStyle(
                    style = TitleScreenStyle
                ) {
                    append("Hello ")
                    append("Alvaro")
                    append("!")
                }
            })
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Let's listen to something cool today",
                color = MusicTheme.colors.secondary,
                fontSize = Small
            )
        }
        Box(
            modifier = Modifier
                .weight(0.12f)
                .aspectRatio(1f)
                .background(MusicTheme.colors.backgroundVariant, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            BadgedBox(
                badge = {
                    Badge(containerColor = MusicTheme.colors.primary, content = {})
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notification",
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MusicSpotTheme { HomeScreen() }
}