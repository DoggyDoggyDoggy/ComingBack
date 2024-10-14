package denys.diomaxius.comingback.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Button
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import denys.diomaxius.comingback.data.Datasource
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
                    label = { Text(text = "Screen 1") },
                    selected = false,
                    onClick = { appViewModel.change_page("BASIC LOGIC") }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Screen 2") },
                    selected = false,
                    onClick = { appViewModel.change_page("EMPTY") }
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
            if (appUiState.page == "BASIC LOGIC") {
                GuideScreen(
                    modifier = Modifier.padding(it),
                    guideViewModel = GuideViewModel(Datasource.basicLogicGuides)
                )
            }

            if (appUiState.page == "EMPTY") {
                Text(
                    modifier = Modifier.padding(it).fillMaxSize(),
                    text = "Empty",
                    fontSize = 32.sp
                )
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


@Composable
fun GuideScreen(
    modifier: Modifier,
    guideViewModel: GuideViewModel
) {
    val guideUiState by guideViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B2838)),
        //.verticalScroll(rememberScrollState()) //Not sure if needed. Maybe just block rotation
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            text = guideUiState.topic,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF5A9FC1),
            textAlign = TextAlign.Center
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            painter = painterResource(id = guideUiState.image),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Not gate image"
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            text = stringResource(guideUiState.description),
            fontSize = 15.sp,
            color = Color(0xFF969696),
            textAlign = TextAlign.Left
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = { guideViewModel.previousGuide() }
            ) {
                Text(text = "Prev Gate")
            }

            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = { guideViewModel.nextGuide() }
            ) {
                Text(text = "Next Gate")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    App()
}