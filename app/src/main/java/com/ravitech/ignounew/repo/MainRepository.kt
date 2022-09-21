package com.ravitech.ignounew.repo

import android.util.Patterns
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.ravitech.ignounew.model.User
import com.ravitech.ignounew.uital.Resource
import com.ravitech.ignounew.uital.safeCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class MainRepository {
    suspend fun createUser(userName: String, userEmailAddress: String, userPhoneNum: String, userLoginPassword: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val registrationResult = firebaseAuth.createUserWithEmailAndPassword(userEmailAddress, userLoginPassword).await()

                val userId = registrationResult.user?.uid!!
                val newUser = User(userName, userEmailAddress, userPhoneNum)
                databaseReference.child(userId).setValue(newUser).await()
                Resource.Success(registrationResult)
            }
        }
    }
    suspend fun login(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }

/*
    fun login(userEmailAddress: String, userLoginPassword: String): Resource<AuthResult>? {

        return null
    }
*/

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference = FirebaseDatabase.getInstance().getReference("users")




}