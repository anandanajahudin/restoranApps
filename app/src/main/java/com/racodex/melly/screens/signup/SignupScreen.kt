package com.racodex.melly.screens.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.racodex.melly.R
import com.racodex.melly.components.CustomInputField
import com.racodex.melly.sealed.Screen
import com.racodex.melly.sealed.UiState
import com.racodex.melly.ui.theme.Dimension

@Composable
fun SignupScreen(
    signupViewModel: SignupViewModel = hiltViewModel(),
) {
    val uiState by remember { signupViewModel.uiState }
    val emailOrPhone by remember { signupViewModel.emailOrPhone }
    val password by remember { signupViewModel.password }

    Column(
        modifier = Modifier.fillMaxSize(

        )
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1.copy(fontSize = 48.sp),
            color = MaterialTheme.colors.primary,
            fontFamily = FontFamily.Default,
        )
        Spacer(modifier = Modifier.height(Dimension.pagePadding.times(2)))
        /** Register info input section */
        CustomInputField(
            modifier = Modifier
                .shadow(
                    elevation = Dimension.elevation,
                    shape = MaterialTheme.shapes.large,
                )
                .fillMaxWidth(),
            value = emailOrPhone ?: "",
            onValueChange = {
                signupViewModel.updateEmailOrPhone(value = it.ifBlank { null })
            },
            placeholder = "Email or Phone ...",
            textStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
            padding = PaddingValues(
                horizontal = Dimension.pagePadding,
                vertical = Dimension.pagePadding.times(0.7f),
            ),
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onBackground,
            imeAction = ImeAction.Next,
            shape = MaterialTheme.shapes.large,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(end = Dimension.pagePadding.div(2))
                        .size(Dimension.mdIcon.times(0.7f)),
                    painter = painterResource(id = R.drawable.ic_profile_empty),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                )
            },
            onFocusChange = { },
            onKeyboardActionClicked = { },
        )
        Spacer(modifier = Modifier.height(Dimension.pagePadding))
        CustomInputField(
            modifier = Modifier
                .shadow(
                    elevation = Dimension.elevation,
                    shape = MaterialTheme.shapes.large,
                )
                .fillMaxWidth(),
            value = password ?: "",
            onValueChange = {
                signupViewModel.updatePassword(value = it.ifBlank { null })
            },
            placeholder = "Password ...",
            visualTransformation = PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Medium),
            padding = PaddingValues(
                horizontal = Dimension.pagePadding,
                vertical = Dimension.pagePadding.times(0.7f),
            ),
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onBackground,
            imeAction = ImeAction.Done,
            shape = MaterialTheme.shapes.large,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .padding(end = Dimension.pagePadding.div(2))
                        .size(Dimension.mdIcon.times(0.7f)),
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.4f),
                )
            },
            onFocusChange = { },
            onKeyboardActionClicked = { },
        )
        /** The login button */
        Spacer(modifier = Modifier.height(Dimension.pagePadding))
        Button(
            modifier = Modifier
                .padding(all = Dp(10F))
                .fillMaxWidth()
                .shadow(
                    elevation = if (uiState !is UiState.Loading) Dimension.elevation else Dimension.zero,
                    shape = MaterialTheme.shapes.large,
                ),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.secondary
            ),
            onClick = {
            },

            // below line is use to set or
            // button as enable or disable.
            enabled = true,
            shape = MaterialTheme.shapes.large,
        ){Text(text = "Register", color = Color.White)
        }

//        val uiState by remember { signupViewModel.uiState }
//        when (uiState) {
//            is UiState.Idle -> {}
//            is UiState.Loading -> {}
//            is UiState.Success -> {}
//            is UiState.Error -> {}
//        }
    }
}