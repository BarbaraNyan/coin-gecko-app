package com.example.trainee_test.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainee_test.R
import com.example.trainee_test.adapters.CryptoAdapter
import com.example.trainee_test.cryptolist.CryptoListViewModel
import com.example.trainee_test.databinding.FragmentCryptoListBinding
import com.example.trainee_test.model.CryptoItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ARG_PARAM_CURRENCY_NAME = "CurrencyName"

@AndroidEntryPoint
class CryptoListFragment : Fragment(), CryptoAdapter.OnItemClickListener {
    private var paramCurrName: String? = null

    private lateinit var binding: FragmentCryptoListBinding
    private lateinit var cryptoAdapter: CryptoAdapter
    private val cryptoList = arrayListOf<CryptoItem>()
    private var repeatTimes = 3
    private val cryptoListViewModel: CryptoListViewModel by viewModels()

    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoAdapter = CryptoAdapter(this, ArrayList())

        binding.apply {
            rvUSDItems.apply {
                adapter = cryptoAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }

    override fun onStart() {
        super.onStart()
        arguments?.let {
            paramCurrName = it.getString(ARG_PARAM_CURRENCY_NAME)
        }
        cryptoListViewModel.getAllCoins("1", currency = paramCurrName.toString())
        callAPI()
    }

    private fun callAPI(){
        val progressBar = binding.progressBar
        CoroutineScope(Dispatchers.Main).launch {
            repeat(repeatTimes){
                cryptoListViewModel._cryptoListValue.collect{value->
                    when {
                        value.isLoading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        value.error.isNotBlank() -> {
                            findNavController().navigate(R.id.action_CryptoListFragment_to_errorFragment)
                            progressBar.visibility = View.GONE
                            repeatTimes = 0
                            Toast.makeText(context, value.error, Toast.LENGTH_LONG).show()
                        }
                        value.cryptoList.isNotEmpty() -> {
                            progressBar.visibility = View.GONE
                            repeatTimes = 0
                            cryptoList.addAll(value.cryptoList)
                            cryptoAdapter.setData(cryptoList as ArrayList<CryptoItem>)
                        }
                    }
                    delay(1000)
                }
            }
        }
    }

    override fun openCryptoDescription(id: String, name: String) {
        bundle.putString("CryptoId", id)
        bundle.putString("CryptoName", name)
        findNavController().navigate(R.id.action_CryptoListFragment_to_descriptionFragment, bundle)
    }

}