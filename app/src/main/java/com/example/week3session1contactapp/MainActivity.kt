package com.example.week3session1contactapp

import android.R.attr.end
import android.R.attr.label
import android.R.attr.text
import android.R.attr.top
import android.R.id.edit
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
//import com.example.week2session1main.Contacts
import com.example.week3session1contactapp.ui.theme.Week3Session1contactappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val listOfContacts by remember {
                mutableStateOf(mutableStateListOf<Contacts>())
            }
            var searchvalue by remember { mutableStateOf("") } // it is written here becuase now we are finding the data by typing here , if it present insode the searchbox(), then will will not be able to find anything
            //by tyooing here, now we will connect this searchvalue to other datas.
            val filteredcontactlist = listOfContacts.filter {
                it.firstname.contains(searchvalue, ignoreCase = true) //ignoreCase = true means it will not be case sensitive
                        ||  it.lastname.contains(searchvalue, ignoreCase = true)
                        || it.number.contains(searchvalue, ignoreCase = true)


        }
            Column() {
                searchbox(searchvalue,{searchvalue=it})     // {searchvalue=it} means we passing the lambda
                contactlist(filteredcontactlist, {listOfContacts.remove(it)}) // it is basically currentContact

            }
            Fabaddcontact1(listOfContacts)

        }
    }
}








@Composable
//@Preview
fun searchbox(searchvalue:String, onValueChange:(String)->Unit) {   //onValueChange:(String)->Unit), so iots a lambda
    // so whatever written inside the searchbox shoupd be independent of searchbox , cuz we are now finding contacts by typing inside the searchbox, so comment out
//var searchvalue by remember { mutableStateOf("") }

    OutlinedTextField(value=searchvalue, onValueChange = {  newtypedvalue ->
       //  searchvalue = newTypedValue   // if i allow it to stay like this, then searchvalue will throw an error , saying that val cant be reassigned, means whatever passed as perameter , we can just
        // read the value , we cant redefine the value
// now i jjust have to invoke the tha lambda
onValueChange(newtypedvalue)
    },
     modifier = Modifier
         .fillMaxWidth()
         .padding(top = 30.dp, start = 12.dp, end = 12.dp),
    label={Text(text="search contact")},
        shape= RoundedCornerShape(30.dp),
        leadingIcon = { // same as  another function "trailingIcon" can load the icon at the last.
            Image(painter = painterResource(id = R.drawable.search), contentDescription = "icon",
                modifier=Modifier.size(24.dp))
        })

}
@Composable
//@Preview
fun contactlist(listOfContacts:List<Contacts>,onDeleteClicked:(Contacts)->Unit) { // onDeleteClicked is type of function which willl return nothing but a unit value
    // jkSpacer(modifier = Modifier.padding(12.dp))
   // since list of contacts ko hum bahar se lenge so we added this as paremeters
    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)

    ){
        items(listOfContacts){
            val currentContact=it
            singleContactUi(currentContact, {onDeleteClicked(currentContact)})

        }
    }
}
@Composable
//@Preview
fun Fabaddcontact(contact:Contacts){
    Box(modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = { /*TODO*/ },modifier= Modifier.padding(16.dp),
            containerColor = Color.White) {
            Icon(painter = painterResource(id =R.drawable.icons8_plus), contentDescription = "plus")
        }
    }
}
// this is for the making of buttom sheet.

@OptIn(ExperimentalMaterial3Api::class) // this ModalBottomSheet is available only in Material3
@Composable
//@Preview
fun Fabaddcontact1(listOfContacts: MutableList<Contacts>){ // the miutableList actually means that we can edit the list
    var firstnamevalue by remember { mutableStateOf("") }
    var lasttnamevalue by remember { mutableStateOf("") }
    var numbervalue by remember { mutableStateOf("") }
    var firstnameempty by remember { mutableStateOf(false) }
    var lastnameempty by remember { mutableStateOf(false) }
    var numberempty by remember { mutableStateOf(false) }


    //we have to make the bottomsheeet like a textfield. so -
    val context= LocalContext.current
    var bottomsheetopen by remember { mutableStateOf(false) } //bottumsheetopen is a variable and ModalBottomsheet is a function.
    Box(modifier=Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) { // now we want ki when we click the plus icon , then only the bottomsheet opens up
        FloatingActionButton(onClick = { bottomsheetopen=true},modifier= Modifier.padding(16.dp),
            containerColor = Color.White) {
            Icon(painter = painterResource(id =R.drawable.icons8_plus), contentDescription = "plus")
        }
    }
   if (bottomsheetopen){ // (if (bottomsheetopen)): The ModalBottomSheet will only be displayed when bottomsheetopen is true , and ai2 katya true hb jatya ami click krim plus t
    ModalBottomSheet(onDismissRequest = { bottomsheetopen=false }) {
        //onDismissRequest = { bottomsheetopen = false } inside ModalBottomSheet:
        //This means that when the user interacts outside the bottom sheet or swipes it down, the sheet will close by setting bottomsheetopen = false.
        Column() {
            OutlinedTextField(value= firstnamevalue,onValueChange = {firstnamevalue=it },
                modifier= Modifier.fillMaxWidth()
                    .padding(start=12.dp,end =12.dp,top=12.dp),
                label = {Text(text="first name")},
               isError = firstnameempty        // okay this is use to show that somethimng is error in the edit box ,
                // means if write somthing wrong , thgen the box turns red
            )
            OutlinedTextField(value= lasttnamevalue,onValueChange = {lasttnamevalue=it },
                modifier= Modifier.fillMaxWidth()
                    .padding(start=12.dp,end =12.dp,top=12.dp),
                label = {Text(text="last name")},
                isError = lastnameempty
            )
            OutlinedTextField(value= numbervalue,onValueChange = {numbervalue=it },
                modifier= Modifier.fillMaxWidth()
                    .padding(start=12.dp,end =12.dp,top=12.dp),
                label = {Text(text="number")},
                isError = numberempty
            )
            Button(onClick = {
                //get all the data from tyhe outlined edit field and then create contact data class and then add this contact to the the listOfContacts
                firstnameempty =firstnamevalue.isBlank()
                lastnameempty = lasttnamevalue.isBlank()
                numberempty=numbervalue.isBlank()

           if(firstnameempty|| lastnameempty || numberempty){       // firstnameempty|| lastnameempty || numberempty initially these are false, if  (false){ } else (true){ they make the contact }


                }else {     //else they make the contact
                    val contact= Contacts(firstnamevalue,lasttnamevalue,numbervalue)
                    listOfContacts.add(contact)
                    firstnamevalue =""
                    lasttnamevalue=""
                    numbervalue="" // the above three lines means the previous text will eb gone after saving
                    bottomsheetopen=false // this line means the bottomsheet will disappear after saving the info
                    firstnameempty=false
               lastnameempty=false
               numberempty=false
               // we again write false on above variables just to reset the valu
                }

                // this line means the bottomsheet will disappear after saving the info
            },modifier=Modifier.fillMaxWidth().padding(12.dp)) {
                Text(text="create contact")
            }
        }
    }
}}
// i just made this function to see the preview pf the textfiled.
@Composable
//@Preview
fun textfield(){
    var firstnamevalue by remember { mutableStateOf("") }
    OutlinedTextField(value = firstnamevalue, onValueChange = {firstnamevalue=it},
modifier= Modifier.fillMaxWidth()
    .padding(start=12.dp,end =12.dp,top=12.dp),
        label = {Text(text="first name")})

}
@Composable
//@Preview
fun singleContactUi(contact: Contacts,onDeleteClicked:(Contacts) -> Unit){
    Spacer(modifier = Modifier.padding(12.dp))
    Box(modifier=Modifier
        .fillMaxWidth()
        .background(color = Color.Black)
        .padding(8.dp)
        .clickable(){
            onDeleteClicked(contact)

            //delete the item.. actually we dont delete it from here , we will pass the function innsinglecontact ui , which will delete
            // , cuz we dont have the access to the losu from here, the only access we can get is from the set content.
        }){
//        Image(painter=painterResource(id=R.drawable.sample),
//            contentDescription = "profile",
//            modifier=Modifier
//                .size(64.dp)
//                .clip(CircleShape),
            //contentScale=ContentScale.Crop)
        Box(modifier=Modifier.size(40.dp).background(color=Color.LightGray,shape=CircleShape),
            contentAlignment = Alignment.Center) {
            Text(text=contact.firstname[0].uppercase(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color=Color.White)
        }
            Text(
                text = "${contact.firstname} ${contact.lastname}",
                modifier = Modifier.padding(start = 74.dp),
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
            Text(
                text = contact.number,
                modifier = Modifier.padding(start = 74.dp, top = 36.dp),
                style = TextStyle(color = Color.White, fontSize = 16.sp)
            )

    }


}

data class Contacts(
    val firstname: String,
    val lastname:String,
    val number:String
)