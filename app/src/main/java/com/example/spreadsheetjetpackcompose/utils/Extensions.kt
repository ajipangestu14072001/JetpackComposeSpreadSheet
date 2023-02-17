package com.example.spreadsheetjetpackcompose.utils

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.spreadsheetjetpackcompose.R
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlin.math.abs

val BottomCardShape = Shapes(
    large = RoundedCornerShape(topStart = 80.dp)
)
fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        x2 = abs(x = from.x + to.x) / 2f,
        y2 = abs(x = from.y + to.y) / 2f
    )
}
fun getTempUri(context: Context): Uri {
    val fileName = "temp_image.png"
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
    }
    return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
}

fun signInWithGoogle(resultLauncher: ActivityResultLauncher<Intent>, context: Context) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    val signInIntent = googleSignInClient.signInIntent
    resultLauncher.launch(signInIntent)
}

fun firebaseAuthWithGoogle(firebaseAuth: FirebaseAuth, idToken: String, navController: NavHostController) {
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    firebaseAuth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate(Screen.Profile.route)
                Log.d("TAG", "signInWithCredential:success")
            } else {
                Log.w("TAG", "signInWithCredential:failure", task.exception)
            }
        }
}