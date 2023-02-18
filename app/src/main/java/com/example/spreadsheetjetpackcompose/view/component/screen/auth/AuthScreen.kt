package com.example.spreadsheetjetpackcompose.view.component.screen.auth

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.spreadsheetjetpackcompose.R
import com.example.spreadsheetjetpackcompose.navigation.Screen
import com.example.spreadsheetjetpackcompose.utils.BottomCardShape
import com.example.spreadsheetjetpackcompose.utils.firebaseAuthWithGoogle
import com.example.spreadsheetjetpackcompose.utils.signInWithGoogle
import com.example.spreadsheetjetpackcompose.view.component.screen.dashboard.DashBoardScreen
import com.example.spreadsheetjetpackcompose.view.component.screen.profile.DetailProfile
import com.example.spreadsheetjetpackcompose.view.widget.GoogleSignInButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth

val firebaseAuth = FirebaseAuth.getInstance()

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AuthScreen(
    navController: NavHostController
) {
    val context = LocalContext.current as Activity
    val resultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(
                    firebaseAuth,
                    account.idToken!!,
                    navController = navController
                )
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }
    Box {
        Column(horizontalAlignment = CenterHorizontally) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(color = 0xFFFF74B1)),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = "https://assets.pikiran-rakyat.com/crop/0x0:0x0/x/photo/2023/01/15/2917671927.jpeg"),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = BottomCardShape.large
            ) {
                Box {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = "Wisataku",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 30.dp),
                            color = Color(color = 0xFFFF74B1),
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.",
                            modifier = Modifier.padding(top = 20.dp, start = 40.dp, end = 20.dp),
                            color = Color.Gray,
                            fontSize = 17.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 30.dp, top = 30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (firebaseAuth.currentUser == null) {
                                GoogleSignInButton(
                                    onClick = {
                                        signInWithGoogle(
                                            resultLauncher = resultLauncher,
                                            context = context
                                        )
                                    },
                                    text = "Google SignIn",
                                    backgroundColor = Color(color = 0xFFFF74B1),
                                    textColor = Color.White,
                                    imageResource = R.drawable.google_logo,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                )
                            } else {
//                                DetailProfile(navController = navController)
                                navController.navigate(Screen.DashBoard.route)
//                                DashBoardScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}









