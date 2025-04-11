package com.example.transapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.transapp.R
import com.example.transapp.data.models.TranslatedText
import com.example.transapp.presentation.TranslateScreenState
import com.example.transapp.presentation.TranslateScreenViewModel
import org.koin.androidx.compose.koinViewModel

private const val EMPTY_STRING = ""

@Composable
fun TranslateScreenWrapper() {
    val viewModel: TranslateScreenViewModel = koinViewModel()
    TranslateScreen(viewModel.translateScreenState.collectAsState().value) {
        viewModel.translate(it)
    }
}

@Composable
fun TranslateScreen(screenState: TranslateScreenState, onTranslateClicked: (String) -> Unit) {
    var translateInput by remember {
        mutableStateOf(TextFieldValue(EMPTY_STRING))
    }
    when (screenState) {
        is TranslateScreenState.Loaded -> {
            LoadedTranslatedScreen(screenState, translateInput) {
                translateInput = it
                onTranslateClicked(it.text)
            }
        }

        is TranslateScreenState.Error -> {
            Text(
                text = screenState.error.message.toString(),
                fontSize = 24.sp,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun LoadedTranslatedScreen(
    screenState: TranslateScreenState.Loaded,
    translateInput: TextFieldValue,
    onTranslatedInputChanged: (TextFieldValue) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            value = translateInput,
            onValueChange = onTranslatedInputChanged,
            textStyle = TextStyle(fontSize = 24.sp),
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onTranslatedInputChanged.invoke(TextFieldValue(EMPTY_STRING))
                    },
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.clear)
                )
            }
        )
        HorizontalDivider()
        Text(
            text = screenState.data.translation,
            fontSize = 24.sp,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TranslateScreenPreview() {
    TranslateScreen(
        TranslateScreenState.Loaded(TranslatedText(translation = "HI!"))
    ) {}
}