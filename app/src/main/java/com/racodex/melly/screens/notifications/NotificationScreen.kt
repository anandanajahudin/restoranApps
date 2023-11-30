package com.racodex.melly.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.racodex.melly.R
import com.racodex.melly.sealed.UiState
import com.racodex.melly.ui.theme.Dimension

@Composable
fun NotificationScreen(
    notificationViewModel: NotificationViewModel = hiltViewModel(),
) {
    val uiState by remember { notificationViewModel.uiState }
    LazyColumn(
        modifier = Modifier.background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.spacedBy(Dimension.pagePadding),
        contentPadding = PaddingValues(Dimension.pagePadding),
    ) {
        item {
            Text(
                text = stringResource(id = R.string.notifications),
                style = MaterialTheme.typography.h1.copy(fontSize = 24.sp),
                color = MaterialTheme.colors.onBackground,
                fontFamily = FontFamily.Default,
            )
        }
        when (uiState) {
            is UiState.Loading -> {}
            is UiState.Success -> {

            }
            is UiState.Idle -> {}
            is UiState.Error -> {}
        }
    }
}