package com.example.trainee_test.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trainee_test.R
import com.example.trainee_test.cryptolist.CryptoDescriptionViewModel
import com.example.trainee_test.databinding.FragmentDescriptionBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
    private val cryptoDescriptionViewModel: CryptoDescriptionViewModel by viewModels()
    private var repeatTimes = 3

    override fun onStart() {
        super.onStart()
        cryptoDescriptionViewModel.getCryptoDescrById("bitcoin")
        callCoinDescriptionApi("bitcoin")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun callCoinDescriptionApi(id: String){
        CoroutineScope(Dispatchers.Main).launch {
            repeat(repeatTimes){
                cryptoDescriptionViewModel._cryptoDescrValue.collect{value->
                    when {
                        value.isLoading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        value.error.isNotBlank() -> {
                            binding.progressBar.visibility = View.GONE
                            repeatTimes = 0
                        }
                        value.cryptoDescription!=null-> {
                            binding.progressBar.visibility = View.GONE
                            repeatTimes = 0
                            Picasso.get().load(value.cryptoDescription.image)
                                .into(binding.imgCrypto)
//                            binding.tvCryptoDescription.movementMethod = LinkMovementMethod.getInstance()
//                            binding.tvCryptoDescription.setLinkTextColor(resources.getColor(R.color.red))
//                            binding.tvCryptoDescription.text = Html.fromHtml(value.cryptoDescription.description)
                            binding.tvCryptoDescription.text = value.cryptoDescription.description
                            Linkify.addLinks(binding.tvCryptoDescription, Linkify.WEB_URLS)
                            binding.tvCryptoCategories.text =
                                value.cryptoDescription.categories.joinToString(separator = ", ")
                        }
                    }
                }
            }
        }
    }



}