package denys.diomaxius.comingback.ui

import denys.diomaxius.comingback.data.GuideChapters


enum class Pages() {}
data class AppUiState(
    val page: String = GuideChapters.BasicLogic.displayName
)