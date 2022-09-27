package com.example.trainee_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.trainee_test.databinding.ActivityMainBinding
import com.example.trainee_test.fragments.USDFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        setupActionBarWithNavController(findNavController(R.id.fragHolder))

        val toolbar = binding.myToolbar
        val chipUSD = binding.chipUSD
        val chipEUR = binding.chipEUR
        val chipGroup = binding.chipGroup

        toolbar.title = "Список криптовалют"

//        chipGroup.setOnCheckedStateChangeListener()

        var lastCheckedId = View.NO_ID
        chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.chipEUR -> {
                    findNavController(R.id.fragHolder).navigate(R.id.EURFragment)
                }
                R.id.chipUSD -> findNavController(R.id.fragHolder).navigate(R.id.USDFragment)
            }
//            if(checkedId == View.NO_ID) {
//                // User tried to uncheck, make sure to keep the chip checked
//                group.check(lastCheckedId)
//                return@setOnCheckedChangeListener
//            }
//            lastCheckedId = checkedId

            // New selection happened, do your logic here.


        }

        chipUSD.isChecked = true
//        setSupportActionBar(toolbar)

        chipUSD.setOnClickListener {
            findNavController(R.id.fragHolder).navigate(R.id.USDFragment)
//            supportFragmentManager.beginTransaction().replace(R.id.fragHolder, USDFragment())
//                .commit()
        }
    }
}