package com.example.spreadsheetjetpackcompose.view.component.screen.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spreadsheetjetpackcompose.model.Destination
import com.example.spreadsheetjetpackcompose.ui.theme.TextWhite
import com.example.spreadsheetjetpackcompose.utils.standardQuadFromTo

@Composable
fun DestinationItem(
    destination: Destination
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(destination.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColoredPoint1 = Offset(x = 0f, y = height * 0.3f)
        val mediumColoredPoint2 = Offset(x = width * 0.1f, y = height * 0.35f)
        val mediumColoredPoint3 = Offset(x = width * 0.4f, y = height * 0.05f)
        val mediumColoredPoint4 = Offset(x = width * 0.75f, y = height * 0.7f)
        val mediumColoredPoint5 = Offset(x = width * 1.4f, y = -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(x = width.toFloat() + 100f, y = height.toFloat() + 100f)
            lineTo(x = -100f, y = height.toFloat() + 100f)
            close()
        }

        val lightPoint1 = Offset(x = 0f, y= height * 0.35f)
        val lightPoint2 = Offset(x = width * 0.1f, y = height * 0.4f)
        val lightPoint3 = Offset(x = width * 0.3f, y = height * 0.35f)
        val lightPoint4 = Offset(x = width * 0.65f, y = height.toFloat())
        val lightPoint5 = Offset(x = width * 1.4f, y = -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(x = width.toFloat() + 100f, y = height.toFloat() + 100f)
            lineTo(x = -100f, y = height.toFloat() + 100f)
            close()
        }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            drawPath(
                path = mediumColoredPath,
                color = destination.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = destination.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = destination.title,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = destination.iconId),
                contentDescription = destination.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Explore",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {

                    }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(color = 0xFFFF74B1))
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}