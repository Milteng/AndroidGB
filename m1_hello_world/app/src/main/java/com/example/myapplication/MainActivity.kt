package com.example.myapplication

import android.app.Activity
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    //задаем максимальное количество пассажиров
    val countMaxPassenger:Int = 50;
    //счетчик
    var countPassenger:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }*/

        //binding.textView.text = getText(R.string.test_name);
        //binding.textView.text = binding.textView.context.getText(R.string.test_name);


        //инициализируем начальные значения
        InitStart(binding);

        binding.btnCancel.setOnClickListener{
            InitStart(binding);
        }

        binding.btnPlus.setOnClickListener{
            AccountPassenger(binding,true);
        }

        binding.btnMinus.setOnClickListener{
            AccountPassenger(binding,false);
        }







    }

    fun AccountPassenger(b:ActivityMainBinding, inc:Boolean){
        var diffCount:Int = 0;

        if (inc)
            countPassenger++
        else
            countPassenger--

        if (countPassenger<=0)
            countPassenger = 0;

        b.tvCount.text = countPassenger.toString();

        when  {
            (countPassenger == 0) -> {
                b.tvMessagesUser.setTextColor(getColorStateList(R.color.green));
                //b.tvMessagesUser.setTextColor(android.graphics.Color.parseColor("green"))
                b.btnCancel.visibility = View.INVISIBLE;
                b.tvMessagesUser.text = getText(R.string.msgUsersBegin);
            }
            (countPassenger >0) && (countPassenger < countMaxPassenger) -> {
                //b.tvMessagesUser.setTextColor(android.graphics.Color.parseColor("blue"))
                b.tvMessagesUser.setTextColor(getColorStateList(R.color.blue));
                b.btnCancel.visibility = View.INVISIBLE;
                diffCount = countMaxPassenger-countPassenger;
                var s:String="";
                s = getText(R.string.msgUsersDo).toString() + diffCount.toString();
                b.tvMessagesUser.text =  s;

            }
            else -> {
                b.tvMessagesUser.setTextColor(getColorStateList(R.color.red));
                //b.tvMessagesUser.setTextColor(android.graphics.Color.parseColor("red"))
                b.btnCancel.visibility = View.VISIBLE;
                b.tvMessagesUser.text=getText(R.string.msgUsersEnd);
            }
        }
    }

    fun InitStart(b:ActivityMainBinding){
        b.tvMessagesUser.text = getText(R.string.msgUsersBegin);
        b.btnCancel.visibility = View.INVISIBLE;
        b.tvMessagesUser.setTextColor(getColorStateList(R.color.green));
        //b.tvMessagesUser.setTextColor(android.graphics.Color.parseColor("green"));
        countPassenger = 0;
        b.tvCount.text = countPassenger.toString();
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}*/