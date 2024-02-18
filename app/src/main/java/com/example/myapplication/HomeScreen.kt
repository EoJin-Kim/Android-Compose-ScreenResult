package com.example.myapplication

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    ReceiveScreenResultCallback<String>(navController = navController){
        when(it.fromScreen){
            DetailMenu.DetailMain.name -> {
                Log.d("EJTAG",it.toString())
            }
        }

    }
    Button(
        onClick = {
            navController.navigate("detail")
        }
    ) {
        Text("Detail 화면으로 이동")
    }
}
