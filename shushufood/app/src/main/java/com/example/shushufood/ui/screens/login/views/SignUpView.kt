package com.example.shushufood.ui.screens.login.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shushufood.R
import com.example.shushufood.ui.components.TextInputForSignUp
import com.example.shushufood.ui.components.TextVisuals
import com.example.shushufood.ui.screens.login.models.LoginViewState
import com.example.shushufood.ui.theme.AppTheme

@Composable
fun SignUpView(
    viewState: LoginViewState,
    onEmailFieldChange: (String) -> Unit,
    onPasswordFieldChange: (String) -> Unit,
    onPhoneNumberFieldChange: (String) -> Unit,
    onFullNameFieldChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header = stringResource(id = R.string.email_hint),
            textFieldValue = viewState.emailValue,
            enabled = !viewState.isProgress,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            onTextFieldChange = {
                if (!viewState.isProgress) onEmailFieldChange.invoke(it)
            },
        )
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header = stringResource(id = R.string.password_hint),
            textFieldValue = viewState.passwordValue,
            onTextFieldChange = {
                if (!viewState.isProgress) onPasswordFieldChange.invoke(it)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            textVisuals = TextVisuals.Password,
            enabled = !viewState.isProgress
        )
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header = stringResource(id = R.string.full_name_hint),
            textFieldValue = viewState.fullNameValue,
            onTextFieldChange = {
                if (!viewState.isProgress) onFullNameFieldChange.invoke(it)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
            enabled = !viewState.isProgress
        )
        TextInputForSignUp(
            modifier = Modifier.padding(top = 0.dp),
            header = stringResource(id = R.string.phone_number_hint),
            textFieldValue = viewState.phoneNumberValue,
            onTextFieldChange = {
                if (!viewState.isProgress) onPhoneNumberFieldChange.invoke(it)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {}
            ),
            textVisuals = TextVisuals.PhoneNumber,
            enabled = !viewState.isProgress
        )
        Button(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                if (!viewState.isProgress)
                    onRegisterClick.invoke()
            },
            shape = RoundedCornerShape(size = 20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppTheme.colors.primaryTextColor
            )
        )
        {
            if (viewState.isProgress) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    color = AppTheme.colors.actionTextColor
                )
            } else {
                Text(
                    text = stringResource(id = R.string.action_register),
                    fontWeight = FontWeight.Medium,
                    color = AppTheme.colors.primaryBackground,
                    style = TextStyle(fontSize = 35.sp)
                )
            }
        }
    }
}