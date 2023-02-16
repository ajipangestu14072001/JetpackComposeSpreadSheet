package com.example.spreadsheetjetpackcompose.view.component.screen.main

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.spreadsheetjetpackcompose.view.component.viewmodel.ViewModelSpreadSheet
import com.example.spreadsheetjetpackcompose.view.widget.Loading
import com.example.spreadsheetjetpackcompose.view.widget.ReusableButton
import com.example.spreadsheetjetpackcompose.view.widget.ReusableTextField
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun MainScreen(
    navController: NavHostController,
    vm: ViewModelSpreadSheet = hiltViewModel()
) {
    val state by vm.state.collectAsState()
    val (name, setName) = remember { mutableStateOf(value = "") }
    val (path, setPath) = remember { mutableStateOf(value = "") }
    var showDialog by remember { mutableStateOf(value = false) }
    val isValidate = name.isNotBlank() && path.isNotBlank()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as Activity
    if (showDialog) Loading()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(backgroundColor = White, elevation = 0.dp) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }

                Text(
                    text = "CRUD Google Sheet",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ReusableTextField(value = name, onValueChange = setName, placeholder = "Nama Lokasi")
            ReusableTextField(value = path, onValueChange = setPath, placeholder = "Path Photo")
            ImageSelector()
        }



        Card(modifier = Modifier.fillMaxWidth(), backgroundColor = White) {
            Column {
                ReusableButton(
                    text = "Tambah Data",
                    onClick = {
                        scope.launch {
                            showDialog = true
                            vm.getAddData(
                                action = "insert",
                                idLokasi = UUID.randomUUID().toString(),
                                namaLokasi = name,
                                pathPhoto = path
                            )
                            Toast.makeText(
                                context,
                                state.result?.body().toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                            showDialog = state.isLoading
                        }
                    },
                    enabled = isValidate
                )
            }
        }
    }
}







