package com.example.trainee_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.trainee_test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val bundle = Bundle()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
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
                    bundle.putString("CurrencyName", "eur")
//                    findNavController(R.id.fragHolder).navigate(R.id.EURFragment)
                }
                R.id.chipUSD -> {
                    bundle.putString("CurrencyName", "usd")
                }
            }
            findNavController(R.id.fragHolder).navigate(R.id.CryptoListFragment, bundle)
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
            findNavController(R.id.fragHolder).navigate(R.id.CryptoListFragment)
//            supportFragmentManager.beginTransaction().replace(R.id.fragHolder, USDFragment())
//                .commit()
        }
    }
}