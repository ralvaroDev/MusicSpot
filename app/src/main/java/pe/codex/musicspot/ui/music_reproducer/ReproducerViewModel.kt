package pe.codex.musicspot.ui.music_reproducer

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.annotation.RawRes
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.codex.musicspot.R
import javax.inject.Inject

@HiltViewModel
class ReproducerViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {


    private val _uiState = MutableStateFlow(MusicReproducerUiState())
    val uiState: StateFlow<MusicReproducerUiState> = _uiState

    private val _musicState = MutableStateFlow<MusicState>(MusicState())
    val musicState: StateFlow<MusicState> = _musicState

    init {
        onInitMusic(context)
    }

    private suspend fun onTreasure() {
        while (_uiState.value.isPlaying){
            delay(1000)
           /* _uiState.value.currentSong?.let {
                Log.d("FATAL", "ojo -> ${it.currentPosition} - ${it.duration}")
            }*/
            _musicState.update {
                it.copy(
                    currentPosition = _uiState.value.currentSong!!.currentPosition
                )
            }
        }
    }


    fun onInitMusic(context: Context) {
        val music: MediaPlayer = MediaPlayer.create(context, R.raw.adele_easy_on_me)
        _uiState.update {
            it.copy(
                currentSong = music,
                musicName = "Easy On Me",
                artistName = "Adele",
            )
        }
    }

    fun onPlayPauseClick(isPlaying: Boolean) {
        _uiState.value.currentSong?.apply {
            if (isPlaying)
                pause()
            else
                start()
        }
        _uiState.update {
            it.copy(isPlaying = !isPlaying)
        }
        viewModelScope.launch(Dispatchers.IO) {
            onTreasure()
        }
    }

}

data class MusicReproducerUiState(
    val currentSong: MediaPlayer? = null,
    val musicName: String = "",
    val artistName: String = "",
    val isPlaying: Boolean = false
)

data class MusicState(
    val currentPosition: Int = 0
)