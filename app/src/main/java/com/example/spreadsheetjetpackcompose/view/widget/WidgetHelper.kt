package com.example.spreadsheetjetpackcompose.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun Loading() = Dialog(
    onDismissRequest = { },
    DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .background(White, shape = RoundedCornerShape(8.dp))
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ReusableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    backgroundColor: Color = Color(0x8FFF74B1),
    cursorColor: Color = White,
    focusedIndicatorColor: Color = Color.Transparent,
    unfocusedIndicatorColor: Color = Color.Transparent,
    shape: Shape = RoundedCornerShape(10.dp),
    textStyle: TextStyle = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
) {
    TextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backgroundColor,
            cursorColor = cursorColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor
        ),
        shape = shape,
        singleLine = true,
        placeholder = { Text(text = placeholder, color = White) },
        textStyle = textStyle
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun ReusableButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color = Color(color = 0xFFFF74B1),
    shape: Shape = RoundedCornerShape(15.dp),
    enabled: Boolean = true,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 20.dp)
        .padding(horizontal = 20.dp),
    contentPadding: PaddingValues = PaddingValues(vertical = 14.dp)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = modifier,
        contentPadding = contentPadding,
        elevation = ButtonDefaults.elevation(),
        shape = shape,
        enabled = enabled,
    ) {
        Text(
            text = text,
            color = White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}