package com.example.decimalconverter

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.decimalconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ convert() }
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
    }

    private fun convert() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }
        val conversion = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_binary -> binaryConvert(cost)
            R.id.option_octal -> octalConvert(cost)
            else -> hexConvert(cost)
        }

        binding.tipResult.text = conversion
    }

    private fun binaryConvert(value: Double): String {
        val intValue = value.toInt()
        return Integer.toBinaryString(intValue)
    }

    private fun octalConvert(value: Double): String {
        val intValue = value.toInt()
        return Integer.toOctalString(intValue)
    }

    private fun hexConvert(value: Double): String {
        val intValue = value.toInt()
        return Integer.toHexString(intValue)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}