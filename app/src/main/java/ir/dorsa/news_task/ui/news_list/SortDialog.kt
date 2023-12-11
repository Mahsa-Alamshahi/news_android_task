package ir.dorsa.news_task.ui.news_list


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ir.dorsa.news_task.R
import ir.dorsa.news_task.ui.components.theme.Red40


@Composable
fun FilterDialog(
    positiveButtonColor: Color = Color(0xFF660000),
    onFilterClick: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(SortTypeEnum.values()[0].sortBy ) }


        Dialog(onDismissRequest = {
            onDismiss()
        }
        ) {


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {



                    Column(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(percent = 10)
                            )
                            .border(width = .5.dp, color = positiveButtonColor),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Spacer(modifier = Modifier.height(height = 36.dp))

                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Sort by:",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(height = 18.dp))
                        HorizontalDivider(modifier = Modifier.height(.5.dp).background(positiveButtonColor))


                        Column(modifier= Modifier.fillMaxWidth()) {

                            SortTypeEnum.values().forEach { text ->

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .selectable(
                                            selected = (text.sortBy == selectedOption),
                                            onClick = {
                                                onOptionSelected(text.sortBy) },
                                            role = Role.RadioButton
                                        )
                                        .padding(start = 8.dp)
                                ) {


                                    RadioButton(
                                        selected = (text.sortBy == selectedOption),
                                        onClick = {
                                            onOptionSelected(text.sortBy)
                                        }
                                    )
                                    Text(
                                        text = text.sortBy,
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                            }
                        }

                        HorizontalDivider(modifier = Modifier.height(.5.dp).background(positiveButtonColor))


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {


                            Button(onClick = {
                                onDismiss()
                            },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(bottomStartPercent = 50),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {

                                Text(text = "Cancel", color= Color.DarkGray)
                                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                Icon(imageVector = Icons.Default.Close,
                                    contentDescription = "Sort",
                                    tint = Color.DarkGray,
                                    modifier = Modifier.size(ButtonDefaults.IconSize))
                            }




                            Button(onClick = {
                                onFilterClick(selectedOption)
                                onDismiss()
                            },
                                modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(bottomEndPercent = 50),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text(text = "Sort", color = Red40)
                                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                Icon(painter = painterResource(id = R.drawable.baseline_sort_24),
                                    contentDescription = "Sort",
                                    tint= Red40,
                                    modifier = Modifier.size(ButtonDefaults.IconSize))
                            }

                        }
                    }


                    Icon(
                        painter = painterResource(id = R.drawable.baseline_sort_24),
                        contentDescription = "Filter Icon",
                        tint = positiveButtonColor,
                        modifier = Modifier
                            .background(color = Color.White, shape = CircleShape)
                            .border(width = 2.dp, shape = CircleShape, color = positiveButtonColor)
                            .padding(all = 16.dp)
                            .align(alignment = Alignment.TopCenter)
                    )



                }
        }

}

@Preview
@Composable
fun PreviewDialog(){
FilterDialog(onFilterClick = {}) {}
}