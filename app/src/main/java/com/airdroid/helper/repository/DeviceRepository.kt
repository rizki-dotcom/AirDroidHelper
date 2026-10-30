package com.airdroid.helper.repository

import com.airdroid.helper.data.Device
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import kotlinx.coroutines.tasks.await

class DeviceRepository {

    private val firestore = Firebase.firestore
    private val auth = Firebase.auth

    suspend fun getDevices(): List<Device> {
        return try {
            val snapshot = firestore.collection("devices")
                .whereEqualTo("userId", auth.currentUser?.uid)
                .orderBy("lastSeen", Query.Direction.DESCENDING)
                .get()
                .await()
            snapshot.documents.mapNotNull { it.toDevice() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun DocumentSnapshot.toDevice(): Device? {
        return Device(
            id = id,
            name = getString("name") ?: "",
            type = getString("type") ?: "",
            status = getString("status") ?: "offline",
            lastSeen = getString("lastSeen") ?: ""
        )
    }
}