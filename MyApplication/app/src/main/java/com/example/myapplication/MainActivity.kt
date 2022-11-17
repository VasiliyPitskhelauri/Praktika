package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.myapplication.Tables.*
import com.example.myapplication.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private val firstFragment = StudentFragment()
    private val secondFragment = AudienceFragment()
    private val thirdFragment = TeachersFragment()
    private val fourFragment = ClubFragment()
    private val fiveFragment = LessonFragment()

    private var currentFragment:Fragment = firstFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(firstFragment)

        val addBtn = findViewById<Button>(R.id.buttonAdd)
        addBtn.setOnClickListener {
            when (currentFragment) {
                firstFragment -> addDataFirst(findViewById<TableLayout>(R.id.firstTableLayout).childCount - 1, "", "", "", "",0f)
                secondFragment -> addDataSecond(findViewById<TableLayout>(R.id.secondTableLayout).childCount - 1, "", "", 0)
                thirdFragment -> addDataThird(findViewById<TableLayout>(R.id.thirdTableLayout).childCount - 1,"","", "")
                fourFragment -> addDataFour(findViewById<TableLayout>(R.id.fourTableLayout).childCount - 1,"", "", "", 0)
                fiveFragment -> addDataFive(findViewById<TableLayout>(R.id.fiveTableLayout).childCount - 1,"", 0, "")
            }
        }

        val saveBtn = findViewById<Button>(R.id.buttonSave)
        saveBtn.setOnClickListener {
            when (currentFragment) {
                firstFragment -> saveDataFirst()
                secondFragment -> saveDataSecond()
                thirdFragment -> saveDataThird()
                fourFragment -> saveDataFour()
                fiveFragment -> saveDataFive()
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.students -> {
                    loadFragment(firstFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.audience -> {
                    loadFragment(secondFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.teachers -> {
                    loadFragment(thirdFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.club -> {
                    loadFragment(fourFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.lesson -> {
                    loadFragment(fiveFragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    fun addDataFirst(col1: Int, col2: String, col3: String, col4: String, col5: String, col6: Float){
        try {
            val tableLayout = findViewById<TableLayout>(R.id.firstTableLayout)
            val dataRow = TableRow(this)

            val idCol = TextView(this)
            idCol.textSize = 14f
            idCol.setPadding(10)
            idCol.gravity = Gravity.CENTER_HORIZONTAL
            idCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            idCol.text = col1.toString()
            dataRow.addView(idCol)

            val firstCol: EditText = EditText(this)
            firstCol.textSize = 14f
            firstCol.setPadding(10)
            firstCol.gravity = Gravity.CENTER_HORIZONTAL
            firstCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            firstCol.setText(col2)
            dataRow.addView(firstCol)

            val secondCol = EditText(this)
            secondCol.textSize = 14f
            secondCol.setPadding(10)
            secondCol.gravity = Gravity.CENTER_HORIZONTAL
            secondCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            secondCol.setText(col3)
            dataRow.addView(secondCol)

            val thirdCol = EditText(this)
            thirdCol.textSize = 14f
            thirdCol.setPadding(10)
            thirdCol.gravity = Gravity.CENTER_HORIZONTAL
            thirdCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            thirdCol.setText(col4)
            dataRow.addView(thirdCol)

            val fourCol = EditText(this)
            fourCol.textSize = 14f
            fourCol.setPadding(10)
            fourCol.gravity = Gravity.CENTER_HORIZONTAL
            fourCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            fourCol.setText(col5)
            dataRow.addView(fourCol)

            val fiveCol = EditText(this)
            fiveCol.textSize = 14f
            fiveCol.setPadding(10)
            fiveCol.gravity = Gravity.CENTER_HORIZONTAL
            fiveCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            fiveCol.setText(col6.toString())
            dataRow.addView(fiveCol)

            dataRow.setOnLongClickListener {
                tableLayout.removeView(it)
                true
            }

            tableLayout.addView(dataRow)
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    fun addDataSecond(col1: Int, col2: String, col3: String, col4: Int){
        try {
            val tableLayout = findViewById<TableLayout>(R.id.secondTableLayout)
            val dataRow = TableRow(this)

            val idCol = TextView(this)
            idCol.textSize = 14f
            idCol.setPadding(10)
            idCol.gravity = Gravity.CENTER_HORIZONTAL
            idCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            idCol.text = col1.toString()
            dataRow.addView(idCol)

            val firstCol: EditText = EditText(this)
            firstCol.textSize = 14f
            firstCol.setPadding(10)
            firstCol.gravity = Gravity.CENTER_HORIZONTAL
            firstCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            firstCol.setText(col2)
            dataRow.addView(firstCol)

            val secondCol = EditText(this)
            secondCol.textSize = 14f
            secondCol.setPadding(10)
            secondCol.gravity = Gravity.CENTER_HORIZONTAL
            secondCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            secondCol.setText(col3)
            dataRow.addView(secondCol)

            val thirdCol = EditText(this)
            thirdCol.textSize = 14f
            thirdCol.setPadding(10)
            thirdCol.gravity = Gravity.CENTER_HORIZONTAL
            thirdCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            thirdCol.setText(col4.toString())
            dataRow.addView(thirdCol)

            dataRow.setOnLongClickListener {
                tableLayout.removeView(it)
                true
            }

            tableLayout.addView(dataRow)
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    fun addDataThird(col1: Int, col2: String, col3: String, col4: String){
        try {
            val tableLayout = findViewById<TableLayout>(R.id.thirdTableLayout)
            val dataRow = TableRow(this)

            val idCol = TextView(this)
            idCol.textSize = 14f
            idCol.setPadding(10)
            idCol.gravity = Gravity.CENTER_HORIZONTAL
            idCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            idCol.text = col1.toString()
            dataRow.addView(idCol)

            val firstCol: EditText = EditText(this)
            firstCol.textSize = 14f
            firstCol.setPadding(10)
            firstCol.gravity = Gravity.CENTER_HORIZONTAL
            firstCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            firstCol.setText(col2)
            dataRow.addView(firstCol)

            val secondCol = EditText(this)
            secondCol.textSize = 14f
            secondCol.setPadding(10)
            secondCol.gravity = Gravity.CENTER_HORIZONTAL
            secondCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            secondCol.setText(col3)
            dataRow.addView(secondCol)

            val thirdCol = EditText(this)
            thirdCol.textSize = 14f
            thirdCol.setPadding(10)
            thirdCol.gravity = Gravity.CENTER_HORIZONTAL
            thirdCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            thirdCol.setText(col4)
            dataRow.addView(thirdCol)

            dataRow.setOnLongClickListener {
                tableLayout.removeView(it)
                true
            }

            tableLayout.addView(dataRow)
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    fun addDataFour(col1: Int, col2: String, col3: String, col4: String, col5: Int){
        try {
            val tableLayout = findViewById<TableLayout>(R.id.fourTableLayout)
            val dataRow = TableRow(this)

            val idCol = TextView(this)
            idCol.textSize = 14f
            idCol.setPadding(10)
            idCol.gravity = Gravity.CENTER_HORIZONTAL
            idCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            idCol.text = col1.toString()
            dataRow.addView(idCol)

            val firstCol: EditText = EditText(this)
            firstCol.textSize = 14f
            firstCol.setPadding(10)
            firstCol.gravity = Gravity.CENTER_HORIZONTAL
            firstCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            firstCol.setText(col2)
            dataRow.addView(firstCol)

            val secondCol = EditText(this)
            secondCol.textSize = 14f
            secondCol.setPadding(10)
            secondCol.gravity = Gravity.CENTER_HORIZONTAL
            secondCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            secondCol.setText(col3)
            dataRow.addView(secondCol)

            val thirdCol = EditText(this)
            thirdCol.textSize = 14f
            thirdCol.setPadding(10)
            thirdCol.gravity = Gravity.CENTER_HORIZONTAL
            thirdCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            thirdCol.setText(col4)
            dataRow.addView(thirdCol)

            val fourCol = EditText(this)
            fourCol.textSize = 14f
            fourCol.setPadding(10)
            fourCol.gravity = Gravity.CENTER_HORIZONTAL
            fourCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            fourCol.setText(col5.toString())
            dataRow.addView(fourCol)

            dataRow.setOnLongClickListener {
                tableLayout.removeView(it)
                true
            }

            tableLayout.addView(dataRow)
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    fun addDataFive(col1: Int, col2: String, col3: Int, col4: String){
        try {
            val tableLayout = findViewById<TableLayout>(R.id.fiveTableLayout)
            val dataRow = TableRow(this)

            val idCol = TextView(this)
            idCol.textSize = 14f
            idCol.setPadding(10)
            idCol.gravity = Gravity.CENTER_HORIZONTAL
            idCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            idCol.text = col1.toString()
            dataRow.addView(idCol)

            val firstCol: EditText = EditText(this)
            firstCol.textSize = 14f
            firstCol.setPadding(10)
            firstCol.gravity = Gravity.CENTER_HORIZONTAL
            firstCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            firstCol.setText(col2)
            dataRow.addView(firstCol)

            val secondCol = EditText(this)
            secondCol.textSize = 14f
            secondCol.setPadding(10)
            secondCol.gravity = Gravity.CENTER_HORIZONTAL
            secondCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            secondCol.setText(col3.toString())
            dataRow.addView(secondCol)

            val thirdCol = EditText(this)
            thirdCol.textSize = 14f
            thirdCol.setPadding(10)
            thirdCol.gravity = Gravity.CENTER_HORIZONTAL
            thirdCol.layoutParams = TableRow.LayoutParams(0, ActionBar.LayoutParams.WRAP_CONTENT, 4f)
            thirdCol.setText(col4)
            dataRow.addView(thirdCol)

            dataRow.setOnLongClickListener {
                tableLayout.removeView(it)
                true
            }

            tableLayout.addView(dataRow)
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDataFirst(){
        val dbRef = FirebaseDatabase.getInstance().getReference("Students")
        try {
            val tableLayout = findViewById<TableLayout>(R.id.firstTableLayout)
            var dataList = mutableListOf<Student>()
            for (num in 1 until tableLayout.childCount){
                val tableRow : TableRow = tableLayout.getChildAt(num) as TableRow
                val textView = tableRow.getChildAt(0) as TextView
                val editText1 = tableRow.getChildAt(1) as EditText
                val editText2 = tableRow.getChildAt(2) as EditText
                val editText3 = tableRow.getChildAt(3) as EditText
                val editText4 = tableRow.getChildAt(4) as EditText
                val editText5 = tableRow.getChildAt(5) as EditText

                val data = Student()
                data.id = textView.text.toString().toInt()
                data.group = editText1.text.toString()
                data.firstName = editText2.text.toString()
                data.lastName = editText3.text.toString()
                data.surName = editText4.text.toString()
                data.avarGrade = editText5.text.toString().toFloat()
                dataList.add(data)
                dbRef.child("${data.id}").setValue(data)
            }
            //db.firstUpdate(dataList)

            Toast.makeText(this, "\uD83D\uDC4D", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun saveDataSecond(){
        val dbRef = FirebaseDatabase.getInstance().getReference("Audience")
        try {
            val tableLayout = findViewById<TableLayout>(R.id.secondTableLayout)
            var dataList = mutableListOf<Audience>()
            for (num in 1 until tableLayout.childCount){
                val tableRow : TableRow = tableLayout.getChildAt(num) as TableRow
                val textView = tableRow.getChildAt(0) as TextView
                val editText1 = tableRow.getChildAt(1) as EditText
                val editText2 = tableRow.getChildAt(2) as EditText
                val editText3 = tableRow.getChildAt(3) as EditText

                val data = Audience()
                data.id = textView.text.toString().toInt()
                data.number = editText1.text.toString()
                data.name = editText2.text.toString()
                data.numberOfComputer = editText3.text.toString().toInt()
                dataList.add(data)
                dbRef.child("${data.id}").setValue(data)
            }
            //db.firstUpdate(dataList)
            Toast.makeText(this, "\uD83D\uDC4D", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDataThird(){
        val dbRef = FirebaseDatabase.getInstance().getReference("Teachers")
        try {
            val tableLayout = findViewById<TableLayout>(R.id.thirdTableLayout)
            var dataList = mutableListOf<Teachers>()
            for (num in 1 until tableLayout.childCount){
                val tableRow : TableRow = tableLayout.getChildAt(num) as TableRow
                val textView = tableRow.getChildAt(0) as TextView
                val editText1 = tableRow.getChildAt(1) as EditText
                val editText2 = tableRow.getChildAt(2) as EditText
                val editText3 = tableRow.getChildAt(3) as EditText

                val data = Teachers()
                data.id = textView.text.toString().toInt()
                data.firstName = editText1.text.toString()
                data.lastName = editText2.text.toString()
                data.surName = editText3.text.toString()
                dataList.add(data)
                dbRef.child("${data.id}").setValue(data)
            }
            //db.firstUpdate(dataList)
            Toast.makeText(this, "\uD83D\uDC4D", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun saveDataFour(){
        val dbRef = FirebaseDatabase.getInstance().getReference("Clubs")
        try {
            val tableLayout = findViewById<TableLayout>(R.id.fourTableLayout)
            var dataList = mutableListOf<Club>()
            for (num in 1 until tableLayout.childCount){
                val tableRow : TableRow = tableLayout.getChildAt(num) as TableRow
                val textView = tableRow.getChildAt(0) as TextView
                val editText1 = tableRow.getChildAt(1) as EditText
                val editText2 = tableRow.getChildAt(2) as EditText
                val editText3 = tableRow.getChildAt(3) as EditText
                val editText4 = tableRow.getChildAt(4) as EditText

                val data = Club()
                data.id = textView.text.toString().toInt()
                data.name = editText1.text.toString()
                data.headClub = editText2.text.toString()
                data.cabinet = editText3.text.toString()
                data.countMember = editText4.text.toString().toInt()
                dataList.add(data)
                dbRef.child("${data.id}").setValue(data)
            }
            //db.firstUpdate(dataList)
            Toast.makeText(this, "\uD83D\uDC4D", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveDataFive(){
        val dbRef = FirebaseDatabase.getInstance().getReference("Lessons")
        try {
            val tableLayout = findViewById<TableLayout>(R.id.fiveTableLayout)
            var dataList = mutableListOf<Lesson>()
            for (num in 1 until tableLayout.childCount){
                val tableRow : TableRow = tableLayout.getChildAt(num) as TableRow
                val textView = tableRow.getChildAt(0) as TextView
                val editText1 = tableRow.getChildAt(1) as EditText
                val editText2 = tableRow.getChildAt(2) as EditText
                val editText3 = tableRow.getChildAt(3) as EditText

                val data = Lesson()
                data.id = textView.text.toString().toInt()
                data.title = editText1.text.toString()
                data.amountHours = editText2.text.toString().toInt()
                data.speciality = editText3.text.toString()
                dataList.add(data)
                dbRef.child("${data.id}").setValue(data)
            }
            //db.firstUpdate(dataList)
            Toast.makeText(this, "\uD83D\uDC4D", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        currentFragment = fragment
    }
}