package com.empresa.gastodeviagem

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.empresa.gastodeviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // FORMULA DO CALCULO: DISTANCIA * PRECO / AUTONOMIA

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.editBtnCalc.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.edit_btn_calc) {
            calculate()
        }
    }

    private fun validate(dist: String, price: String, autonomy: String): Boolean {
        return (dist.isEmpty() || price.isEmpty() || autonomy.isEmpty() || autonomy.toFloat() == 0f)
    }

    @SuppressLint("SetTextI18n")
    private fun calculate() {

        val textAutonomy = binding.editAuto.text.toString()
        val textDistance = binding.editDistance.text.toString()
        val textPrice =  binding.editPrice.text.toString()

        val isValid = validate(textDistance, textPrice, textAutonomy)

        if (isValid) {
            Toast.makeText(this, R.string.validation_fields, Toast.LENGTH_LONG).show()
            binding.editTotal.text = "R$ 0"
        } else {
            val intAutonomy = textAutonomy.toFloat()
            val intDistance = textDistance.toFloat()
            val intPrice = textPrice.toFloat()

            val res = intDistance * intPrice / intAutonomy
            val result = "R$ ${ "%.2f".format(res)}"

            binding.editTotal.text = result

            binding.editAuto.text.clear()
            binding.editDistance.text.clear()
            binding.editPrice.text.clear()
        }
    }

}