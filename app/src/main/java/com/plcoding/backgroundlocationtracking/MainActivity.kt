package com.plcoding.backgroundlocationtracking

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.plcoding.backgroundlocationtracking.ui.LocationScreenService
import com.plcoding.backgroundlocationtracking.ui.TextFieldInput
import com.plcoding.backgroundlocationtracking.ui.UserUiState
import com.plcoding.backgroundlocationtracking.ui.UserViewModel
import com.plcoding.backgroundlocationtracking.ui.theme.BackgroundLocationTrackingTheme
import androidx.compose.runtime.collectAsState as collectAsState

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun login(email: String, password: String, userViewModel: UserViewModel) {
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        userViewModel.updateUserLogin(user?.uid, user?.email)
                    } else {
                        Log.w("FAILURE", "signInWithEmail:failure", task.exception)
                    }
                }
        }

        FirebaseApp.initializeApp(this);
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )

        val userViewModel : UserViewModel by viewModels<UserViewModel>()

        setContent {
            BackgroundLocationTrackingTheme {
                val userUiState by userViewModel.uiState.collectAsState()
                if(userUiState.isSignedIn) {
                    LocationScreenService(this, userUiState.email)
                } else {
                    TextFieldInput { email, password -> login(email, password, userViewModel) }
                }
            }
        }
    }
}
