package com.intent.firebaseService

import android.util.Log
//import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseController{

    fun insertLocationData(latitude: String, longitude: String, userDocumentId: String) {
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        val location = mapOf(
            "lat" to latitude,
            "lng" to longitude
        )
        val collectionName = "location"
        db.collection(collectionName).document(userDocumentId)
            .set(location)
            .addOnSuccessListener { Log.d("Firestore Response", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("Firestore Response", "Error writing document", e) }
    }

}