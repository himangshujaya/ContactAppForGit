package com.example.week3session1contactapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
//import com.example.week2session1main.Contacts
import com.example.week3session1contactapp.ui.theme.Week3Session1contactappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            searchbox()

        }
    }
}


@Composable
//@Preview
fun simpleLazyColumn1() {
    val lisofContacts = listOf(
        Contacts(R.drawable.sample,"himanshu","this msg is from himangshu"),
        Contacts(R.drawable.netflix,"raj","this msg is from netflix"),
        Contacts(R.drawable.sample1,"hima","this msg is from hima"),
        Contacts(R.drawable.sample,"rani","this msg is from rani"),
        Contacts(R.drawable.netflix,"ankit","this msg is from ankit"),
        Contacts(R.drawable.sample,"himanshu","this msg is from himangshu"),
        Contacts(R.drawable.netflix,"raj","this msg is from netflix"),
        Contacts(R.drawable.sample1,"hima","this msg is from hima"),
        Contacts(R.drawable.sample,"rani","this msg is from rani"),
        Contacts(R.drawable.netflix,"ankit","this msg is from ankit"),
        Contacts(R.drawable.sample,"himanshu","this msg is from himangshu"),
        Contacts(R.drawable.netflix,"raj","this msg is from netflix"),
        Contacts(R.drawable.sample1,"hima","this msg is from hima"),
        Contacts(R.drawable.sample,"rani","this msg is from rani"),
        Contacts(R.drawable.netflix,"ankit","this msg is from ankit"),
        Contacts(R.drawable.sample,"himanshu","this msg is from himangshu"),
        Contacts(R.drawable.netflix,"raj","this msg is from netflix"),
        Contacts(R.drawable.sample1,"hima","this msg is from hima"),
        Contacts(R.drawable.sample,"rani","this msg is from rani"),
        Contacts(R.drawable.netflix,"ankit","this msg is from ankit"),
        Contacts(R.drawable.sample,"himanshu","this msg is from himangshu"),
        Contacts(R.drawable.netflix,"raj","this msg is from netflix"),
        Contacts(R.drawable.sample1,"hima","this msg is from hima"),
        Contacts(R.drawable.sample,"rani","this msg is from rani"),
        Contacts(R.drawable.netflix,"ankit","this msg is from ankit"),
        Contacts(R.drawable.sample,"himanshu","this msg is from himangshu"),
        Contacts(R.drawable.netflix,"raj","this msg is from netflix"),
        Contacts(R.drawable.sample1,"hima","this msg is from hima"),
        Contacts(R.drawable.sample,"rani","this msg is from rani"),
        Contacts(R.drawable.netflix,"ankit","this msg is from ankit"),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue))
    {
        items(lisofContacts){
            val currentContacts=it
            singleContactUi(currentContacts)
        }
    }



}



@Composable
@Preview
fun searchbox(){
var searchvalue by remember { mutableStateOf("") }
    OutlinedTextField(value=searchvalue, onValueChange = {searchvalue=it},
     modifier = Modifier.fillMaxWidth()
            .padding(top= 30.dp, start=12.dp, end=12.dp),
    label={Text(text="search contact")},
        shape= RoundedCornerShape(30.dp),
        leadingIcon = {
            Image(painter = painterResource(id = R.drawable.search), contentDescription = "icon",
                modifier=Modifier.size(24.dp))
        })

}




@Composable
//@Preview
fun singleContactUi(contact: com.example.week3session1contactapp.Contacts){
    Box(modifier=Modifier.fillMaxWidth()
        .background(color=Color.Black)
        .padding(8.dp)){
        Image(painter=painterResource(id=contact.profileImage),
            contentDescription = "profile",
            modifier=Modifier.size(64.dp)
                .clip(CircleShape),
            contentScale=ContentScale.Crop)
        Text(text= contact.name,
            modifier= Modifier.padding(start=74.dp),
            style= TextStyle(color=Color.White,fontSize=20.sp))
        Text(text= contact.message,
            modifier= Modifier.padding(start=74.dp,top=36.dp),
            style= TextStyle(color=Color.White,fontSize=16.sp))

    }


}
data class Contacts(
    val profileImage:Int,
    val name:String,
    val message:String
)