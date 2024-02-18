package com.example.myapplication

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController) {
    // 예시 버튼, 실제로는 사용자 입력이나 선택에 따라 결과 설정
    Button(onClick = {

        val result = ScreenResult<Box>(
            fromScreen = DetailMenu.DetailMain.name,
            result = Box(1,"box입니다")
        )

        setScreenResultCallback(
            navController = navController,
            resultValue = result
        )
    }) {
        val result = ScreenResult<Box>(
            fromScreen = DetailMenu.DetailMain.name,
            result = Box(1,"box입니다")
        )

        SetBackScreenResultCallback(
            navController = navController,
            enable = true,
            resultValue = result
        )
        Text("결과 설정하고 돌아가기")



    }
}

data class Box(
    val id : Int,
    val name : String
)

interface Menu
enum class DetailMenu() : Menu{
    DetailMain
}