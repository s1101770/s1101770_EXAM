package com.example.myapplication

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.util.Log
import android.view.MotionEvent
import android.view.View.OnClickListener
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String) {

    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "JumpFirst"){
        composable("JumpFirst"){
            FirstScreen(navController = navController)
        }
        composable("JumpSecond"){
            SecondScreen(navController = navController)
        }

    }


}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FirstScreen(navController: NavController) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "作者： 資工二A 胡肇宇")
        Image(painter = painterResource(id = R.drawable.map), contentDescription = "地圖")
    }
    Box(modifier = Modifier.fillMaxSize()){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter { motionEvent ->
                    when(motionEvent.action){
                        MotionEvent.ACTION_DOWN -> {
                            Log.d("now", motionEvent.y.toString())
                            if ( 820f >= motionEvent.x && motionEvent.x >= 790f && motionEvent.y >= 200f && motionEvent.y <= 230f){
                                navController.navigate("JumpSecond")
                            }
                            if ( 1630f >= motionEvent.x && motionEvent.x >= 1600f && motionEvent.y >= 900f && motionEvent.y <= 930f){
                                Toast.makeText(context,"臺中市清水區南社社區發展協會",Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
                    true}
        ) {
            drawRect(
                Color.Blue,
                Offset(790f,200f),
                Size(30f,30f),
            )
            drawRect(
                Color.Blue,
                Offset(1600f,900f),
                Size(30f,30f)
            )
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("JumpFirst")
        }) {
            Text(text = "返回主畫面")
        }
        Image(painter = painterResource(id = R.drawable.center), contentDescription = "center")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}