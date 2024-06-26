package com.example.smartreminder.ui.addedit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.smartreminder.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    onDateSelected: (String) -> Unit
) {
    val datePickerState = rememberDatePickerState(
        // selectableDates = TODO: 2. implement selectable dates
    )
    val millisToLocalDate = datePickerState.selectedDateMillis?.let {
        Instant.ofEpochMilli(it)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
    val dateToString = millisToLocalDate?.let {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        it.format(formatter)
    } ?: "Choose Date"

    var showDialog by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .shadow(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDialog = true
                },
            text = dateToString,
            style = MaterialTheme.typography.headlineMedium
        )
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            onDateSelected(dateToString)
                            showDialog = false
                        }
                    ) {
                        Text(text = "OK")
                    }
                },
                tonalElevation = 10.dp,
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                },
                /* colors = DatePickerDefaults.colors(
                    containerColor = colorResource(id = R.color.surface),
                    currentYearContentColor = colorResource(id = R.color.on_surface),
                    todayDateBorderColor = colorResource(id = R.color.on_surface),
                    dayContentColor = colorResource(id = R.color.on_surface),
                    selectedDayContentColor = colorResource(id = R.color.surface),
                    selectedDayContainerColor = colorResource(id = R.color.on_surface),
                    selectedYearContentColor = colorResource(id = R.color.surface),
                    selectedYearContainerColor = colorResource(id = R.color.on_surface),
                    todayContentColor = colorResource(id = R.color.on_surface),
                    yearContentColor = colorResource(id = R.color.on_surface)
                ) */
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = true
                )
            }
        }
    }
}