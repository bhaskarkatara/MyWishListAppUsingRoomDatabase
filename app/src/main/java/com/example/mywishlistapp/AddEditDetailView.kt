package com.example.mywishlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable

fun AddEditDetailView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
){

    val snackMessage = remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()
     val  scaffoldState = rememberScaffoldState()

   if(id != 0L){
       val wish = viewModel.getAWishById(id).collectAsState(initial = Wish(0L, title = "", description = ""))
       viewModel.wishTitleState = wish.value.title
       viewModel.wishDescriptionState = wish.value.description
   }else{
       viewModel.wishTitleState = ""
       viewModel.wishDescriptionState = ""
   }


    Scaffold (
        scaffoldState = scaffoldState,
        topBar = { AppBarView(title = if(id == 0L) "Add Wish" else "update Wish") {navController.navigateUp()} }
    )

    {
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
                     if(id != 0L){
                         // todo update wish
                         viewModel.updateWish(
                             Wish(
                                 id = id,
                                 title = viewModel.wishTitleState.trim(),
                                 description = viewModel.wishDescriptionState.trim()))
                          snackMessage.value = "wish has been updated"
                     }else {
                         //  add a wish
                         viewModel.addWish(
                             Wish(
                                 title = viewModel.wishTitleState.trim(),
                                 description = viewModel.wishDescriptionState.trim()
                             )
                         )
                         snackMessage.value = "wish has been created"
                     }

                  }else{
                      snackMessage.value = "please fill all fields to create wish"
                  }

                 scope.launch {
                     scaffoldState.snackbarHostState.showSnackbar((snackMessage.value))
                     navController.navigateUp()
                 }
             }) {
                 Text(text =  if(id == 0L) "Add Wish" else "update Wish", style = TextStyle(fontSize = 18.sp))
             }

             Spacer(modifier = Modifier.height(40.dp))
             Text(text = "Made by ❤️Kataraji")
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
@Preview
@Composable
fun WishTest(){
    WishTextField(label = "text", value = "text", onValueChanges = {})
}



