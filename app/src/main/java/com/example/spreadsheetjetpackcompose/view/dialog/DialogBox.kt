package com.example.spreadsheetjetpackcompose.view.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.spreadsheetjetpackcompose.R
import com.example.spreadsheetjetpackcompose.view.widget.ReusableButton

@Composable
fun DialogBox(camera: () -> Unit, gallery: () -> Unit, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(color = Color(color = 0xFFFF74B1)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 16.dp),
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "",
                        alignment = Alignment.Center
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
                    text = "Pilih Sumber Gambar",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    ReusableButton(
                        text = "Gallery",
                        onClick = { gallery() },
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp, bottom = 8.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(10.dp)
                    )
                    ReusableButton(
                        text = "Take Picture",
                        onClick = { camera() },
                        modifier = Modifier
                            .padding(start = 8.dp, end = 16.dp, bottom = 8.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(10.dp)
                    )
                }
            }
        }
    }
}