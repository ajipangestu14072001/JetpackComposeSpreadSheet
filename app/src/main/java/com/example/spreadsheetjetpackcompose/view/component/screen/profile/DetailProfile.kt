package com.example.spreadsheetjetpackcompose.view.component.screen.profile

import android.app.Activity
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.spreadsheetjetpackcompose.R
import com.example.spreadsheetjetpackcompose.view.component.screen.auth.firebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser

@Composable
fun DetailProfile(
    navController : NavHostController,
    user: FirebaseUser? = firebaseAuth.currentUser
) {
    val context = LocalContext.current as Activity
    if (user != null) {
        ProfileScreen(
            profileImage = user.photoUrl!!,
            name = user.displayName!!,
            email = user.email!!,
            signOutClicked = {
                val googleSignInClient: GoogleSignInClient
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()

                googleSignInClient = GoogleSignIn.getClient(context, gso)
                firebaseAuth.signOut()
                googleSignInClient.signOut()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Sign Out Successful", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Sign Out Failed", Toast.LENGTH_SHORT).show()
                    }
            },
            navController = navController
        )
    }
}