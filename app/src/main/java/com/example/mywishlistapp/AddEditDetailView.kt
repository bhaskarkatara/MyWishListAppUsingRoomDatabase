package com.example.mywishlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable

fun AddEditDetailView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
){
    Scaffold (
        topBar = { AppBarView(title = if(id == 0L) "Add Wish" else "update Wish") {navController.navigateUp()} }
    ){
         Column(
             modifier = Modifier
                 .padding(it)
                 .wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center
         ) {
           Spacer(modifier = Modifier.height(10.dp))

             WishTextField(label = "Tittle", value = viewModel.wishTitleState, onValueChanges = {
                 viewModel.onWishTitleChanged(it)
             })

             Spacer(modifier = Modifier.height(10.dp))

             WishTextField(label = "Description", value = viewModel.wishDescriptionState, onValueChanges = {
                 viewModel.onWishDescriptionChanged(it)
             })

             Spacer(modifier = Modifier.height(10.dp))
             Button(onClick = {
                 if(viewModel.wishTitleState.isNotEmpty()
                     && viewModel.wishDescriptionState.isNotEmpty()){
                        // update wish
                 }else{
                     // add wish
                  }
             }) {
                 Text(text =  if(id == 0L) "Add Wish" else "update Wish", style = TextStyle(fontSize = 18.sp))
             }
         }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanges: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanges,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedLabelColor = colorResource(id = R.color.black),
                    unfocusedLabelColor = colorResource(id = R.color.black),

                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black),
                    focusedBorderColor = colorResource(id = R.color.black),
                    unfocusedBorderColor = colorResource(id = R.color.black)
        )
    )
}
@Preview()
@Composable
fun WishTest(){
    WishTextField(label = "text", value = "text", onValueChanges = {})
}



