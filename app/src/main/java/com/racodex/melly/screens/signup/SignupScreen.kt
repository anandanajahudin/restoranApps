package com.racodex.melly.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.racodex.melly.R
import com.racodex.melly.components.CustomButton
import com.racodex.melly.components.CustomInputField
import com.racodex.melly.sealed.UiState
import com.racodex.melly.ui.theme.Dimension

@Composable
fun SignupScreen(
    navHostController: NavHostController,
    signupViewModel: SignupViewModel = hiltViewModel(),
) {

//    val uiState by remember { signupViewModel.uiState }
////    val emailOrPhone by remember { signupViewModel.emailOrPhone }
////    val password by remember { signupViewModel.password }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(Dimension.pagePadding),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Text(
//            text = stringResource(id = R.string.app_name),
//            style = MaterialTheme.typography.h1.copy(fontSize = 48.sp),
//            color = MaterialTheme.colors.primary,
//            fontFamily = FontFamily.Default,
//        )
//        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(2)))
//        /** Login info input section */
//        CustomInputField(
//            modifier = Modifier
//                .shadow(
//                    elevation = Dimension.elevation,
//                    shape = MaterialTheme.shapes.large,
//                )
//                .fillMaxWidth(),
//            placeholder = "Email or Phone ...",
//            textStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
//            padding = PaddingValues(
//                horizontal = Dimension.pagePadding,
//                vertical = Dimension.pagePadding.times(0.7f),
//            ),
//            backgroundColor = MaterialTheme.colors.surface,
//            textColor = MaterialTheme.colors.onBackground,
//            imeAction = ImeAction.Next,
//            shape = MaterialTheme.shapes.large,
//            onValueChange = {
//                signupViewModel.updateEmailOrPhone(value = it.ifBlank { null })
//            },
//            leadingIcon = {
//                Icon(
//                    modifier = Modifier
//                        .padding(end = Dimension.pagePadding.div(2))
//                        .size(Dimension.mdIcon.times(0.7f)),
//                    painter = painterResource(id = R.drawable.ic_profile_empty),
//                    contentDescription = null,
//                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
//                )
//            },
//            onFocusChange = { }
//        ) { }
//        Spacer(modifier = Modifier.height(Dimension.pagePadding))
//        CustomInputField(
//            modifier = Modifier
//                .shadow(
//                    elevation = Dimension.elevation,
//                    shape = MaterialTheme.shapes.large,
//                )
//                .fillMaxWidth(),
//            placeholder = "Password ...",
//            visualTransformation = PasswordVisualTransformation(),
//            textStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
//            padding = PaddingValues(
//                horizontal = Dimension.pagePadding,
//                vertical = Dimension.pagePadding.times(0.7f),
//            ),
//            backgroundColor = MaterialTheme.colors.surface,
//            textColor = MaterialTheme.colors.onBackground,
//            imeAction = ImeAction.Done,
//            shape = MaterialTheme.shapes.large,
//            leadingIcon = {
//                Icon(
//                    modifier = Modifier
//                        .padding(end = Dimension.pagePadding.div(2))
//                        .size(Dimension.mdIcon.times(0.7f)),
//                    painter = painterResource(id = R.drawable.ic_lock),
//                    contentDescription = null,
//                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
//                )
//            },
//            onFocusChange = { },
//        ) { }
//        /** The login button */
//        Spacer(modifier = Modifier.height(Dimension.pagePadding))
//        CustomButton(
//            modifier = Modifier
//                .fillMaxWidth()
//                .shadow(
//                    elevation = if (uiState !is UiState.Loading) Dimension.elevation else Dimension.zero,
//                    shape = MaterialTheme.shapes.large,
//                ),
//            shape = MaterialTheme.shapes.large,
//            padding = PaddingValues(Dimension.pagePadding.div(2)),
//            buttonColor = MaterialTheme.colors.primary,
//            contentColor = MaterialTheme.colors.onPrimary,
//            text = "Submit",
//            enabled = uiState !is UiState.Loading,
//            textStyle = MaterialTheme.typography.button,
//            onButtonClicked = {
//                /** Handle the click event of the login button */
//
//            },
//        )
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = Dimension.pagePadding),
//            contentAlignment = Alignment.Center,
//        ) {
//            Divider()
//            Text(
//                modifier = Modifier
//                    .background(MaterialTheme.colors.background)
//                    .padding(horizontal = Dimension.pagePadding.div(2)),
//                text = "Or",
//                style = MaterialTheme.typography.caption
//                    .copy(
//                        fontWeight = FontWeight.SemiBold,
//                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
//                    ),
//            )
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val uiState by remember { signupViewModel.uiState }
        when (uiState) {
            is UiState.Idle -> {}
            is UiState.Loading -> {}
            is UiState.Success -> {}
            is UiState.Error -> {}
        }
    }
}