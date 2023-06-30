package pe.codex.musicspot.ui

val recentPlayedMocked = listOf(
    RecentPlayed(
        1,
        "Bad Bunny",
        "Un verano sin ti",
        "https://i.scdn.co/image/ab67616d00001e0249d694203245f241a1bcaa72"
    ),
    RecentPlayed(
        2,
        "KAROL G",
        "MANANA SERA BONITO",
        "https://i.scdn.co/image/ab67616d00001e0282de1ca074ae63cb18fce335"
    ),
    RecentPlayed(
        3,
        "Bizarrap, Rauw Alejandro",
        "Rauw Alejandro: Bzrp Music Sessions, Vol. 56",
        "https://i.scdn.co/image/ab67616d00001e0295dcc1f2e2a2ad2050f7f41d"
    ),
    RecentPlayed(
        4,
        "Feid",
        "FELIZ CUMPLEAÑOS FERXXO TE PIRATEAMOS EL ÁLBUM",
        "https://i.scdn.co/image/ab67616d00001e0273456ed697350847810e52b3"
    ),
    RecentPlayed(
        5,
        "Grupo Frontera, Bad Bunny",
        "un x100to",
        "https://i.scdn.co/image/ab67616d00001e02716c0b0ad51594ff788b5f06"
    ),
    RecentPlayed(
        6,
        "Myke Towers",
        "LA VIDA ES UNA",
        "https://i.scdn.co/image/ab67616d00001e020656d5ce813ca3cc4b677e05"
    )
)

val dailyRecommendationData = listOf(
    DailyRecommendation(
        idRecommendation = 1,
        imageUrl = "https://dailymix-images.scdn.co/v2/img/ab6761610000e5ebf316bf60f021402dcdb9f0b9/1/es-ES/default"
    ),
    DailyRecommendation(
        idRecommendation = 2,
        imageUrl = "https://dailymix-images.scdn.co/v2/img/ab6761610000e5eb6481401e529e475116702a29/2/es-ES/default"
    ),
    DailyRecommendation(
        idRecommendation = 3,
        imageUrl = "https://dailymix-images.scdn.co/v2/img/ab6761610000e5ebf316bf60f021402dcdb9f0b9/3/es-ES/default"
    ),
    DailyRecommendation(
        idRecommendation = 4,
        imageUrl = "https://dailymix-images.scdn.co/v2/img/ab6761610000e5ebf316bf60f021402dcdb9f0b9/4/es-ES/default"
    ),
    DailyRecommendation(
        idRecommendation = 5,
        imageUrl = "https://dailymix-images.scdn.co/v2/img/ab6761610000e5ebf316bf60f021402dcdb9f0b9/5/es-ES/default"
    ),
    DailyRecommendation(
        idRecommendation = 6,
        imageUrl = "https://dailymix-images.scdn.co/v2/img/ab6761610000e5ebf316bf60f021402dcdb9f0b9/6/es-ES/default"
    )
)

val artistData = listOf(
    ArtistData(
        idArtist = 1,
        name = "Rauw Alejandro",
        imageUrl = "https://i.scdn.co/image/ab676161000051746584a85fa605e4cba91ae250"
    ),
    ArtistData(
        idArtist = 2,
        name = "Shakira",
        imageUrl = "https://i.scdn.co/image/ab67616100005174284894d68fe2f80cad555110"
    ),
    ArtistData(
        idArtist = 3,
        name = "Feid",
        imageUrl = "https://i.scdn.co/image/ab676161000051747310314e15b4f6aee1135d46"
    ),
    ArtistData(
        idArtist = 4,
        name = "Daddy Yankee",
        imageUrl = "https://i.scdn.co/image/ab67616100005174584f79075db4e0c9304a7f85"
    ),
    ArtistData(
        idArtist = 5,
        name = "Myke Towers",
        imageUrl = "https://i.scdn.co/image/ab6761610000517420e3ebcfcc2fecf17387e873"
    ),
    ArtistData(
        idArtist = 6,
        name = "Karol G",
        imageUrl = "https://i.scdn.co/image/ab676161000051746a0594d5bff11a7e0c7ceb1a"
    ),
    ArtistData(
        idArtist = 7,
        name = "Ozuna",
        imageUrl = "https://i.scdn.co/image/ab676161000051742aa71241ff5532e3ceeb86a1"
    )
)

data class DailyRecommendation(
    val idRecommendation: Int,
    val imageUrl: String
)

data class ArtistData(
    val idArtist: Int,
    val name: String,
    val imageUrl: String
)

data class RecentPlayed(
    val idMusic: Int,
    val artist: String,
    val songName: String,
    val imageUrl: String
)