package denys.diomaxius.comingback

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import denys.diomaxius.comingback.ui.App
import denys.diomaxius.comingback.ui.theme.ComingBackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComingBackTheme {
                App()
            }
        }
    }
}