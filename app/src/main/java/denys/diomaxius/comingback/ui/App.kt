package denys.diomaxius.comingback.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import denys.diomaxius.comingback.data.Datasource
import denys.diomaxius.comingback.data.GuideChapters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun App(
    appViewModel: AppViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    val appUiState by appViewModel.uiState.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = GuideChapters.BasicLogic.displayName) },
                    selected = false,
                    onClick = { appViewModel.changePage(GuideChapters.BasicLogic.displayName) }
                )

                NavigationDrawerItem(
                    label = { Text(text = GuideChapters.Arithmetic.displayName) },
                    selected = false,
                    onClick = { appViewModel.changePage(GuideChapters.Arithmetic.displayName) }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    drawerState = drawerState,
                    scope = scope
                )
            }
        ) {
            when (appUiState.page) {
                GuideChapters.BasicLogic.displayName -> {
                    GuideScreen(
                        modifier = Modifier.padding(it),
                        guideViewModel = GuideViewModel(Datasource.basicLogicGuides)
                    )
                }

                GuideChapters.Arithmetic.displayName -> {
                    GuideScreen(
                        modifier = Modifier.padding(it),
                        guideViewModel = GuideViewModel(Datasource.arithmeticGuides)
                    )
                }

                else -> {
                    Text(
                        modifier = Modifier.padding(it).fillMaxSize(),
                        text = "Empty",
                        fontSize = 32.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TopBar(
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF171D25)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            modifier = Modifier
                .padding(start = 15.dp)
                .clickable {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
            imageVector = Icons.Outlined.Menu,
            contentDescription = "",
            tint = Color.White
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Turing Complete Guide",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    App()
}