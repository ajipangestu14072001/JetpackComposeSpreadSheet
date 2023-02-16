package com.example.spreadsheetjetpackcompose.view.component.screen.all

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.spreadsheetjetpackcompose.model.Record
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.example.spreadsheetjetpackcompose.view.widget.Loading
import com.example.spreadsheetjetpackcompose.view.component.viewmodel.ViewModelSpreadSheet
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AllDataScreen(
    navController: NavHostController,
    vm: ViewModelSpreadSheet = hiltViewModel()
) {
    val state by vm.state.collectAsState()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val selectedRecord = remember { mutableStateOf<Record?>(null) }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(backgroundColor = Color.White, elevation = 5.dp) {
            IconButton(onClick = { navController.navigate(Screen.Main.route) }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
            }
            Text(text = "Semua Data", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
        when {
            state.allData == null -> Loading()
            state.allData?.records!!.isNotEmpty() -> {
                val locations = state.allData?.records!!.map { record ->
                    Record(
                        TIMESTAMP = record.TIMESTAMP,
                        ID_LOKASI = record.ID_LOKASI,
                        NAMA_LOKASI = record.NAMA_LOKASI,
                        PHOTO = record.PHOTO
                    )
                }
                LocationList(locations) { record ->
                    scope.launch {
                        selectedRecord.value = record
                        bottomSheetState.show()
                    }
                }
            }
            else -> Loading()
        }
    }

    LaunchedEffect(Unit) {
        vm.getAllData(action = "readAll")
    }

    if (selectedRecord.value != null) {
        ModalBottomSheetLayout(
            sheetState = bottomSheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetBackgroundColor = Color.White,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = selectedRecord.value!!.NAMA_LOKASI,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(text = selectedRecord.value!!.ID_LOKASI, fontSize = 12.sp, color = Color.Gray)
                    Text(text = selectedRecord.value!!.TIMESTAMP, fontSize = 12.sp, color = Color.Gray)
                    Image(
                        painter = rememberAsyncImagePainter(model = selectedRecord.value!!.PHOTO),
                        contentDescription = "location photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 16.dp, bottom = 16.dp)
                    )
//                    Button(
//                        onClick = {
//                            scope.launch {
//                                bottomSheetState.hide()
//                            }
//                        },
//                        modifier = Modifier.align(Alignment.End)
//                    ) {
//                        Text(text = "Close")
//                    }
                }
            }
        ) {
        }
    }
}

@Composable
fun LocationList(locations: List<Record>, onItemClick: (Record) -> Unit) {
    LazyColumn {
        items(locations) { location ->
            DataItem(record = location, onItemClick = onItemClick)
            Divider()
        }
    }
}

@Composable
fun DataItem(record: Record, onItemClick: (Record) -> Unit) {
    val context = LocalContext.current as Activity
    Box(
        modifier = Modifier
            .clickable(
                onClick = {
                    Toast.makeText(context, record.NAMA_LOKASI, Toast.LENGTH_SHORT).show()
                    onItemClick(record)
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = record.PHOTO),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = record.NAMA_LOKASI, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = record.TIMESTAMP, fontSize = 8.sp, color = Color.Gray)
                }
                Text(text = record.ID_LOKASI, fontSize = 12.sp, color = Color.Gray)
                Text(text = record.NAMA_LOKASI, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}



