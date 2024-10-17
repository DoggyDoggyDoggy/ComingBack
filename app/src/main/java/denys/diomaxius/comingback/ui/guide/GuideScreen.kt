package denys.diomaxius.comingback.ui.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import denys.diomaxius.comingback.ui.App

@Composable
fun GuideScreen(
    modifier: Modifier,
    guideViewModel: GuideViewModel
) {
    val guideUiState by guideViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1B2838))
            .verticalScroll(rememberScrollState()) //Not sure if needed. Maybe just block rotation
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp)
        ) {
            Image(
                painter = painterResource(id = guideUiState.image),
                contentScale = ContentScale.FillWidth,
                contentDescription = "Not gate image",
                modifier = Modifier.fillMaxWidth()
            )

            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = "Overlay Icon",
                modifier = Modifier
                    .align(Alignment.TopEnd) // Change position as needed
                    .size(48.dp) // Adjust icon size
                    .padding(10.dp)
                    .clickable { guideViewModel.add_favorite_guide() }
                , // Padding from the top or sides
                tint = Color.White // Tint the icon if required
            )
        }

        /*
        Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            painter = painterResource(id = guideUiState.image),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Not gate image"
        )
        */

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
                onClick = { guideViewModel.previousGuide() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF324763)
                )
            ) {
                Text(text = "Prev Gate")
            }

            Button(
                modifier = Modifier.padding(horizontal = 20.dp),
                onClick = { guideViewModel.nextGuide() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF324763)
                )
            ) {
                Text(text = "Next Gate")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    GuideScreen(
        modifier = Modifier,
        guideViewModel = GuideViewModel(Datasource.basicLogicGuides)
    )
}