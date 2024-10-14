package denys.diomaxius.comingback.ui

import androidx.lifecycle.ViewModel
import denys.diomaxius.comingback.data.Datasource
import denys.diomaxius.comingback.data.NUMBER_OF_GUIDES
import denys.diomaxius.comingback.model.Guide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GuideViewModel(guides: List<Guide>) : ViewModel() {


    private var guideList: List<Guide> = guides

    private var guideNumber: Int = 0

    private val _uiState = MutableStateFlow(
        GuideUiState(
            topic = guideList[guideNumber].topic,
            image = guideList[guideNumber].image,
            description = guideList[guideNumber].description
        )
    )
    val uiState: StateFlow<GuideUiState> = _uiState.asStateFlow()

    fun nextGuide() {
        if (NUMBER_OF_GUIDES > guideNumber + 1) {
            guideNumber++
            _uiState.value = GuideUiState(
                topic = guideList[guideNumber].topic,
                image = guideList[guideNumber].image,
                description = guideList[guideNumber].description
            )
        }
    }

    fun previousGuide() {
        if (guideNumber - 1 >= 0) {
            guideNumber--
            _uiState.value = GuideUiState(
                topic = guideList[guideNumber].topic,
                image = guideList[guideNumber].image,
                description = guideList[guideNumber].description
            )
        }
    }

}