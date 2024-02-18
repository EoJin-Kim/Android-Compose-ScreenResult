package com.example.myapplication

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.gson.Gson


@Composable
fun <T>ReceiveScreenResultCallback(
    navController: NavController,
    resultCallback : (ScreenResult<*>) -> Unit,
) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val result = savedStateHandle?.getLiveData<String>("key")

    // 결과가 변경될 때마다 호출됩니다.
    LaunchedEffect(result) {
        savedStateHandle?.get<String>("key")?.let { resultValue ->

            val screenResult = Gson().fromJson(resultValue, ScreenResult::class.java)

            resultCallback(screenResult)
            // 결과값으로 무언가 처리하기
            println("결과: $resultValue")
            // 결과 사용 후에는 필요에 따라 삭제할 수 있습니다.
            //savedStateHandle.remove<String>("key")
        }
    }
}

@Composable
fun <T>SetBackScreenResultCallback(
    navController: NavController,
    enable : Boolean,
    resultValue : ScreenResult<T>
) {
    BackHandler(enabled = enable) {
        setScreenResultCallback<T>(
            navController = navController,
            resultValue = resultValue,
        )
    }
}

fun <T>setScreenResultCallback(
    navController: NavController,
    resultValue : ScreenResult<T>
){
    val resultJson = Gson().toJson(resultValue)
    navController.previousBackStackEntry?.savedStateHandle?.set("key", resultJson)
    navController.popBackStack()
}

data class ScreenResult<T>(
    val status : ScreenResultStatus = ScreenResultStatus.SUCCESS,
    val fromScreen : String,
    val result : T,
){

}

enum class ScreenResultStatus{
    SUCCESS, FALSE
}