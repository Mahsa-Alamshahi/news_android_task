package ir.dorsa.news_task.ui.survey

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import ir.dorsa.news_task.R
import ir.dorsa.news_task.common.isConnectionOn
import ir.dorsa.news_task.ui.components.theme.Red40


@Composable
fun SurveyScreenRoute() {

    val viewModel: SurveyViewModel = hiltViewModel()

    SurveyScreen(
        viewModel.surveyState.value,
        viewModel::addComment
    )
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun SurveyScreen(
    surveyState: SurveyState,
    addComment: () -> Unit
) {


    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp


    var description by remember { mutableStateOf("") }
    var isErrorInDescriptions by rememberSaveable { mutableStateOf(false) }


    var userName by remember { mutableStateOf("") }
    var isErrorInUsername by rememberSaveable { mutableStateOf(false) }


    var isButtonClicked by remember {
        mutableStateOf(false)
    }



    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {


                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(start = 8.dp, top = 10.dp, end = 8.dp),
                    value = userName,
                    onValueChange = { newUsername ->
                        userName = newUsername
                        isErrorInUsername = newUsername.isEmpty()
                    },
                    label = { Text(stringResource(R.string.username)) },
                    isError = isErrorInUsername,
                    supportingText = {
                        if (userName.isEmpty()) {
                            Text("Enter username.")
                        }
                    }
                )


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .height(heightInDp / 2)
                        .padding(start = 8.dp, top = 10.dp, end = 8.dp),
                    shape = RoundedCornerShape(5.dp),
                    value = description,
                    label = {
                        Text(text = stringResource(id = R.string.descriptions))
                    },
                    onValueChange = {descriptionText ->
                        description = descriptionText
                        isErrorInDescriptions = descriptionText.isEmpty()
                    },
                    isError = isErrorInDescriptions,
                    supportingText = {
                        if (description.isEmpty()) {
                            Text("Enter descriptions.")
                        }
                    }
                )

                Button(
                    onClick = {
                        if ((!isErrorInUsername) && (!isErrorInDescriptions)) {
                            isButtonClicked = true
                            if (context.isConnectionOn()) {
                                addComment()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please check your internet connection.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else if ((isErrorInUsername) && (isErrorInDescriptions.not())) {
                            Toast.makeText(context, "Please enter username.", Toast.LENGTH_SHORT)
                                .show()
                        } else if ((isErrorInUsername.not()) && (isErrorInDescriptions)) {
                            Toast.makeText(
                                context,
                                "Please enter descriptions.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            Toast.makeText(
                                context,
                                "Please enter username and descriptions.",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Red40),
                    border = BorderStroke(1.dp, Red40),
                    shape = RoundedCornerShape(0),
                ) {
                    Text(text = stringResource(R.string.register))
                }


                if (isButtonClicked) {
                    if (surveyState.isLoading) {
                        Toast.makeText(context, "Please wait ...", Toast.LENGTH_SHORT).show()
                    } else if (surveyState.error.isNotBlank()) {
                        Toast.makeText(context, surveyState.error, Toast.LENGTH_SHORT).show()
                        isButtonClicked = false
                    } else if (surveyState.surveyList.isEmpty()) {
                        Toast.makeText(context, "Error occured.", Toast.LENGTH_SHORT).show()
                        isButtonClicked = false
                    } else {
                        Toast.makeText(
                            context,
                            surveyState.surveyList[0].survey,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        isButtonClicked = false
                    }

                }
            }
        }
    }
}