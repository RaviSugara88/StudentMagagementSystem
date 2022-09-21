package com.ravitech.ignounew.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ravitech.ignounew.MainActivity
import com.ravitech.ignounew.MainViewModel
import com.ravitech.ignounew.databinding.ActivityLoginBinding
import com.ravitech.ignounew.R
import com.ravitech.ignounew.model.User
import com.ravitech.ignounew.uital.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
//https://www.section.io/engineering-education/coroutines-and-realtime-database-in-firebase-authentication-in-android/

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private val mainViewModel :MainViewModel by viewModels()
    private  var dbReference: DatabaseReference? =null
    private  var firebaseDatabase: FirebaseDatabase?=null
    //some code
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleView()
        handleOb()
    }
    fun deleteData(){

    }


    private fun handleOb() {
        mainViewModel.userRegistrationStatus.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.registerProgress.isVisible = true
                }
                is Resource.Success -> {
                    binding.registerProgress.isVisible = false
                    Toast.makeText(applicationContext, "Registered Successfully", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    binding.registerProgress.isVisible = false
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        mainViewModel.userSignUpStatus.observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.registerProgress.isVisible = true
                }
                is Resource.Success -> {
                    binding.registerProgress.isVisible = false
                    Toast.makeText(applicationContext, "Logged In Successfully", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    binding.registerProgress.isVisible = false
                    Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun handleView() {


        // below line is used to get the instance
        // of our Firebase database.
      //  val languages = resources.getStringArray(R.array.p)
     //   dbReference = FirebaseDatabase.getInstance("users")
      val programList=  resources.getStringArray(R.array.program)

        binding.apply {
            val adapter
                    = ArrayAdapter(this@LoginActivity,
                android.R.layout.simple_list_item_1, programList)
            program.setAdapter(adapter)

            dob.setOnClickListener {
                selectDate { date->
                    dateOfBirth.editText?.setText(date)
                }
            }

            login.setOnClickListener {
               // loginUser()
               // firebaseDatabase = FirebaseDatabase.getInstance()
                /*get firebase refernce */
               // dbReference = firebaseDatabase?.getReference("user")
                // dbReference?.child("newUsr")?.setValue(User("Ravindra","ravi@yopmail.com","9809876545","rss@123"))

                val dbRf = FirebaseDatabase.getInstance().getReference("user")
                val checkUser = dbRf.orderByChild("newUsr")
                xtnLog(checkUser.toString(),"Test1234")

                checkUser.addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {

                        if (snapshot.exists()){
                            /*DataSnapshot { key = user, value = {newUsr={password=rss@123, phone=9809876545, name=Ravindra, email=ravi@yopmail.com}} }*/
                            val userData = snapshot.child("newUsr")
                            xtnLog(userData.child("password").value.toString(),"Test1234")
                        }

                    }
                    override fun onCancelled(error: DatabaseError) {

                        xtnLog(error.toString(),"Test1234")

                    }

                })

            }
            regis.setOnClickListener {

                firebaseDatabase = FirebaseDatabase.getInstance()
                /*get firebase refernce */
                dbReference = firebaseDatabase?.getReference("user")
                /*add simple data firebase
                * dbReference?.setValue("test")
                * add data with key
                * dbReference?.child("userId")?.setValue("ertret")
                * */

                dbReference?.child("newUsr")?.setValue(User("Ravindra","ravi@yopmail.com","9809876545","rss@123"))
                // dbReference?.database?.reference
                //createUser()
            }

            btnLogin.setOnClickListener {
              //  xtnNavigate2<MainActivity>()
              //  findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                val email = userName.editText?.text.toString()
                val pass = dateOfBirth.editText?.text.toString()
                val program = program.text.toString()
                if (email.isEmpty()) userName.error="Enter ${getString(R.string.enrollment_no)}" else userName.error=""
                if (pass.isEmpty()) dateOfBirth.error="Enter ${getString(R.string.date_of_birth)}" else dateOfBirth.error =""
                if (program.isEmpty()) type.error="Select Program" else type.error =""
                if (email.isNotEmpty()&&pass.isNotEmpty()&&program.isNotEmpty()){
                    if (!email.equals(EMAIL)) userName.error = "Enter valid ${getString(R.string.enrollment_no)}"
//                    else if (!pass.equals(PASSWORD)) dateOfBirth.error =
//                        "Enter valid ${getString(R.string.date_of_birth)}"
                    else
                        xtnNavigate2<MainActivity>()
                     //   findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

            }
        }
    }
    /*Registering a user*/

    fun createUser(){
        mainViewModel.createUser(
            "ravi",
            "ravi123@yopmail.com",
            "9540052146",
            "rss12345"
        )
    }

    fun loginUser(){
        mainViewModel.signInUser(
            "ravi123@yopmail.com",
            "rss12345"
        )
    }
/*
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
*/
    /*Login user*/
/*
    suspend fun login(email: String, password: String): Resource<AuthResult> {
        return withContext(Dispatchers.IO) {
            safeCall {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                Resource.Success(result)
            }
        }
    }
*/
}