package com.example.spreadsheetjetpackcompose.view.component.screen.main

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.example.spreadsheetjetpackcompose.utils.getTempUri
import com.example.spreadsheetjetpackcompose.view.dialog.DialogBox
import com.example.spreadsheetjetpackcompose.view.widget.Loading
import com.example.spreadsheetjetpackcompose.view.widget.ReusableButton
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@Composable
fun ImageSelector() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val bitmap = remember { mutableStateOf<ImageBitmap?>(null) }

    var showDialog by remember { mutableStateOf(false) }

    var showLoading by remember { mutableStateOf(false) }

    val storageRef = Firebase.storage.reference.child("images")

    val realtimeDatabaseRef = Firebase.database.reference

    val context = LocalContext.current
    val cropLauncher = rememberLauncherForActivityResult(CropImageContract()) { result ->
        result.uriContent?.let { imageUri = it }
        result.error?.let { println(it.toString()) }
    }
    if (showLoading) Loading()

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmapImage ->
            bitmapImage?.let {
                bitmap.value = it.asImageBitmap()
                val tempUri = getTempUri(context)
                context.contentResolver.openOutputStream(tempUri).use { outputStream ->
                    it.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                }
                imageUri = tempUri
                cropLauncher.launch(CropImageContractOptions(imageUri, CropImageOptions()))
            }
        }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let { cropLauncher.launch(CropImageContractOptions(uri, CropImageOptions())) }
        }

    imageUri.takeIf { it != null }?.let { uri ->
        bitmap.value = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri).asImageBitmap()
        } else {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(context.contentResolver, uri)
            ).asImageBitmap()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        bitmap.value?.let {
            Image(
                bitmap = it,
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
        }

        ReusableButton(
            text = "Pilih Gambar",
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        )

        ReusableButton(
            text = "Unggah Gambar",
            onClick = {
                if (imageUri != null) {
                    val fileName = "${System.currentTimeMillis()}_${imageUri?.lastPathSegment}"
                    val uploadTask = storageRef.child(fileName).putFile(imageUri!!)
                    uploadTask.addOnSuccessListener {
                        storageRef.child(fileName).downloadUrl.addOnSuccessListener { uri ->
                            realtimeDatabaseRef.child("images").push().setValue(uri.toString())
                            showLoading = true
                        }
                        showLoading = false
                        Toast.makeText(context, "Upload berhasil", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(context, "Upload gagal", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Belum ada gambar yang dipilih", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (showDialog) {
            DialogBox(
                camera = { cameraLauncher.launch(null) },
                gallery = { galleryLauncher.launch("image/*") },
                onDismiss = { showDialog = false }
            )
        }
    }
}
