package com.example.autopasswordgenerator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.autopasswordgenerator.ui.theme.buttonColor
import com.example.autopasswordgenerator.ui.theme.deepBlue
import com.example.autopasswordgenerator.ui.theme.textColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Generator()

        }
    }
}

@Composable
fun Generator(modifier: Modifier = Modifier) {

    var password by remember { mutableStateOf("Tap (Generate New password) button ") }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val toast = Toast.makeText(
        LocalContext.current, "copied", Toast.LENGTH_SHORT
    )
    Box(modifier = Modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(deepBlue)
                .padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(textColor)
                    .height(70.dp), contentAlignment = Alignment.Center

            ) {
                Text(
                    text = password,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { password = generatePassword() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.height(60.dp)

                ) {
                    Text(text = "Generate New password")
                }
                Button(
                    onClick = {
                        clipboardManager.setText(AnnotatedString((password)))


                        toast.show()

                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ), modifier = Modifier.height(60.dp)
                ) {
                    Text(text = "Copy")
                }
            }
            Spacer(modifier = Modifier.height(150.dp))
        }
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = null,
            alignment = Alignment.BottomStart,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun generatePassword(): String {
    val chars =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefhhbhbbbhfeeesccbbnjuuyyytrtrredsnkp/;'`12345678876543212345654323456543ghijklmnopqrstuvwxyz0123456789!@#\$%^&*()_-+=<>?"
    return (1..12).map { chars.random() }.joinToString("")
}


@Preview
@Composable
private fun Pepep() {
    Generator()
}

