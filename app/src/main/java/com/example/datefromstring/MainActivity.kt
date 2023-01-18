package com.example.datefromstring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.datefromstring.ui.theme.DateFromStringTheme
import org.pojava.datetime.DateTime
import org.pojava.datetime.DateTimeConfig
import org.pojava.datetime.IDateTimeConfig
import java.util.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateFromStringTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box{
                        var input by remember {
                            mutableStateOf("")
                        }
                        val date by remember {
                            derivedStateOf {
                                Decode(input)
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ){
                            TextField(
                                value = input,
                                onValueChange = {
                                    input = it
                                }
                            )
                            Text(Decode(date))
                        }
                    }

                }
            }
        }
    }

    private fun Decode(input: String): String {
        return DateParser().parse(input).toString()
    }
}

