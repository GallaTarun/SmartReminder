package com.example.smartreminder.ui.addedit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.smartreminder.R
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen() {
    var showModalBottomSheet by remember { mutableStateOf(true) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    if(showModalBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showModalBottomSheet = false
            },
            sheetState = modalBottomSheetState,
            containerColor = colorResource(id = R.color.surface),
            contentColor = colorResource(id = R.color.on_surface),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            dragHandle = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalDivider(
                        modifier = Modifier
                            .width(32.dp),
                        color = Color.LightGray,
                        thickness = 4.dp
                    )
                }
            }
        ) {
            ModalBottomSheetContent()
        }
    }
}

@Composable
fun ModalBottomSheetContent() {
    val reminderTitle = remember { mutableStateOf("") }
    val reminderNotes = remember { mutableStateOf("") }
    var showDatePicker = remember { mutableStateOf(false) }
    val showTimePicker = remember { mutableStateOf(false) }
    var selectedDate = remember { mutableStateOf("") }
    var selectedTime = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "New Reminder",
            style = MaterialTheme.typography.headlineMedium,
        )
        OutlinedTextField(
            value = reminderTitle.value,
            onValueChange = { reminderTitle.value = it },
            placeholder = { Text("Title") },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.on_surface),
                unfocusedBorderColor = Color.LightGray
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
        OutlinedTextField(
            value = reminderNotes.value,
            onValueChange = { reminderNotes.value = it },
            placeholder = { Text("Notes", color = colorResource(id = R.color.on_surface)) },
            singleLine = false,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.on_surface),
                unfocusedBorderColor = Color.LightGray,
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                onClick = {
                    showTimePicker.value = false
                    showDatePicker.value = true
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, color = colorResource(id = R.color.on_surface)),
                colors = CardColors(
                    containerColor = colorResource(id = R.color.surface),
                    contentColor = colorResource(id = R.color.on_surface),
                    disabledContentColor = colorResource(id = R.color.on_surface),
                    disabledContainerColor = colorResource(id = R.color.surface)
                )
            ) {
                Row (
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(vertical = 4.dp, horizontal = 12.dp),
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = null
                    )
                    Text(
                        text = if(selectedDate.value == "") "Choose date" else selectedDate.value
                    )
                }
            }
            Card(
                onClick = {
                    showDatePicker.value = false
                    showTimePicker.value = true
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, color = colorResource(id = R.color.on_surface)),
                colors = CardColors(
                    containerColor = colorResource(id = R.color.surface),
                    contentColor = colorResource(id = R.color.on_surface),
                    disabledContentColor = colorResource(id = R.color.on_surface),
                    disabledContainerColor = colorResource(id = R.color.surface)
                )
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 4.dp, horizontal = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = null
                    )
                    Text(
                        text = if(selectedTime.value == "") "Choose time" else selectedDate.value
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO: 3. submit the input values and create reminder*/ },
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.on_surface),
                    contentColor = colorResource(id = R.color.surface),
                    disabledContainerColor = colorResource(id = R.color.on_surface),
                    disabledContentColor = colorResource(id = R.color.surface)
                )
            ) {
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }

        if(showDatePicker.value) {
            DatePickerWithDialog { it: String ->
                selectedDate.value = it
                showDatePicker.value = false
            }
        }
        if(showTimePicker.value) {
            TimePickerWithDialog { it: String ->
                selectedTime.value = it
                showTimePicker.value = false
            }
        }
    }
}