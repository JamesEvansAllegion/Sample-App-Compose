package com.example.sampleappcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sampleappcompose.launchApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

@Composable
fun SearchScreen(displayLogs: MutableState<Boolean>) {
    SearchApp(displayLogs)
}
@Composable
fun SearchApp(displayLogs: MutableState<Boolean>) {
    val logs = remember { mutableStateListOf<String>() }
    val apiResponse = remember { mutableStateOf("") }
    val requestData = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Form(
            requestData = requestData.value,
            onValueChange = { newValue -> requestData.value = newValue },
            onSubmit = {
                coroutineScope.launch {
                    launchApiCall(requestData.value, apiResponse, logs)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResponseView(response = apiResponse.value)

        Spacer(modifier = Modifier.height(16.dp))

        if(displayLogs.value){
            LogsView(logs = logs)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchPreview() {
//    SearchApp()
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(requestData: String, onValueChange: (String) -> Unit, onSubmit: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = requestData,
            onValueChange = onValueChange,
            label = { Text("Enter movie title") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onSubmit,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun ResponseView(response: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "API Response:")
        Text(text = response)
    }
}

@Composable
fun LogsView(logs: List<String>) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "API Logs:")
        LazyColumn {
            items(items = logs) { log -> Text(text = log) }
        }
    }
}