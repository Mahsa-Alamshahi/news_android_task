package ir.dorsa.news_task.ui.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp


@Composable
fun SurveyScreen(){


    var userName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }


    Column(modifier= Modifier.fillMaxSize()) {


        TextField(
            value = userName,
            onValueChange = { newText ->
                userName = newText
            },
            label = { Text("Enter Text") },
            isError = userName.isNotEmpty()
        )


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth().height(120.dp)
                .padding(start = 15.dp, top = 10.dp, end = 15.dp),
            shape = RoundedCornerShape(5.dp),
            value = description,
            onValueChange = { description = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            maxLines = 30
        )



        Button(
            onClick = {
            },
        ) {
            Text(text = "Ok")
        }

    }
}