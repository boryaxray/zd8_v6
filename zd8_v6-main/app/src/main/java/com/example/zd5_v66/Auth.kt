package com.example.zd5_v66

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make

class Auth : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var spinner: Spinner
    private lateinit var pref: SharedPreferences
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        pref = getPreferences(MODE_PRIVATE)

        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.auth_button)

        button.setOnClickListener(View.OnClickListener {
            if (email.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
                if (pass.text.toString().length >= 8) {
                    if (email.text.toString().contains("@", ignoreCase = true) && email.text.toString().contains(".", ignoreCase = true))
                    {
                        when(spinner.selectedItem.toString())
                        {
                            "Преподаватель" -> {

                                if (((pref.getString("emailTeacher", "")) == "") && ((pref.getString("passwordTeacher", "")) == "")
                                ) {
                                    val ed = pref.edit()
                                    ed.putString("emailTeacher", email.text.toString())
                                    ed.putString("passwordTeacher", pass.text.toString())
                                    ed.apply()

                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)

                                } else if (((pref.getString(
                                        "emailTeacher",
                                        ""
                                    )) == email.text.toString()) && ((pref.getString(
                                        "passwordTeacher",
                                        ""
                                    )) == pass.text.toString())
                                ) {

                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    val alert = AlertDialog.Builder(this)
                                        .setTitle("Неверно")
                                        .setMessage("Данные введены неверно")
                                        .setPositiveButton("Попробовать еще раз",null)
                                        .create()
                                        .show()
                                }
                            }
                            "Студент" -> {

                                if (((pref.getString("emailStudent", "")) == "") && ((pref.getString("passwordStudent", "")) == "")
                                ) {
                                    val ed = pref.edit()
                                    ed.putString("emailStudent", email.text.toString())
                                    ed.putString("passwordStudent", pass.text.toString())
                                    ed.apply()

                                    val intent = Intent(this, ViewTeachersActivity::class.java)
                                    startActivity(intent)

                                } else if (((pref.getString(
                                        "emailStudent",
                                        ""
                                    )) == email.text.toString()) && ((pref.getString(
                                        "passwordStudent",
                                        ""
                                    )) == pass.text.toString())
                                ) {

                                    val intent = Intent(this, ViewTeachersActivity::class.java)
                                    startActivity(intent)

                                } else {
                                    val alert = AlertDialog.Builder(this)
                                        .setTitle("Неверно")
                                        .setMessage("Данные введены неверно")
                                        .setPositiveButton("Попробовать еще раз",null)
                                        .create()
                                        .show()
                                }
                            }
                            "Приёмная комиссия" -> {
                                if (((pref.getString("emailComission", "")) == "") && ((pref.getString("passwordComission", "")) == "")
                                ) {
                                    val ed = pref.edit()
                                    ed.putString("emailComission", email.text.toString())
                                    ed.putString("passwordComission", pass.text.toString())
                                    ed.apply()

                                    val intent = Intent(this, ViewStudentsActivity::class.java)
                                    startActivity(intent)

                                } else if (((pref.getString(
                                        "emailComission",
                                        ""
                                    )) == email.text.toString()) && ((pref.getString(
                                        "passwordComission",
                                        ""
                                    )) == pass.text.toString())
                                ) {
                                    val intent = Intent(this, ViewStudentsActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    val alert = AlertDialog.Builder(this)
                                        .setTitle("Ошибка")
                                        .setMessage("Данные введены неверно")
                                        .setPositiveButton("Попробовать еще раз",null)
                                        .create()
                                        .show()
                                }
                            }
                        }
                    }
                    else
                    {
                        val alert = AlertDialog.Builder(this)
                            .setTitle("Ошибка")
                            .setMessage("Введите верную почту")
                            .setPositiveButton("Попробовать еще раз",null)
                            .create()
                            .show()
                    }

                } else {

                    val alert = AlertDialog.Builder(this)
                        .setTitle("Ошибка")
                        .setMessage("Пароль должен быть не менее 8 символов")
                        .setPositiveButton("Попробовать еще раз",null)
                        .create()
                        .show()
                }

            } else {

                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("Введите почту и пароль")
                    .setPositiveButton("Попробовать еще раз",null)
                    .create()
                    .show()

            }
        })
    }
}