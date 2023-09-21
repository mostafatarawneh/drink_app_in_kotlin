package com.mostafa.drinkapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    lateinit var btn_submit : Button
    lateinit var drop_downmenu : AutoCompleteTextView
    lateinit var tv_price :TextView
    val orangejuice = "Orange Juice"
    val applejuice = "Apple Juice"
    val mangojuice = "Mango Juice"
    val kewijuice = "Kewi Juice"
    val values = mapOf(orangejuice to 15,applejuice to 20,mangojuice to 25 , kewijuice to 30)
    var array_orders_name = arrayOf(orangejuice,applejuice,mangojuice,kewijuice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_submit =findViewById(R.id.btn_submit)
        drop_downmenu=findViewById(R.id.dropdown_menu)
        tv_price=findViewById(R.id.tv_price)
        val adaptor = ArrayAdapter(this,R.layout.tv_drop_downmenu,array_orders_name)
        drop_downmenu.setAdapter(adaptor)
       drop_downmenu.addTextChangedListener {
           when(drop_downmenu.text.toString()){
               orangejuice -> tv_price.setText(values.get(orangejuice).toString())
               applejuice -> tv_price.setText((values.get(applejuice).toString()))
               mangojuice -> tv_price.setText(values.get(mangojuice).toString())
               kewijuice-> tv_price.setText(values.get(kewijuice).toString())
       }

        }
        btn_submit.setOnClickListener {
            val message = "I want to Order ${drop_downmenu.text.toString()}"
            val int = Intent(Intent.ACTION_SENDTO)
            int.data= Uri.parse("mailto:")
            int.putExtra(Intent.EXTRA_EMAIL, arrayOf("CityDrink@gmail.com"))
            int.putExtra(Intent.EXTRA_SUBJECT,"Order")
            int.putExtra(Intent.EXTRA_TEXT,message)
            if (int.resolveActivity(packageManager)!= null){
                startActivity(int)
            }else{
                Toast.makeText(this,"No Activity To Handle Intent",Toast.LENGTH_SHORT).show()
            }

        }
    }
}