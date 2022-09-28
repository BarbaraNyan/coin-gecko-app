package com.example.trainee_test.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainee_test.R
import com.example.trainee_test.adapters.CryptoAdapter
import com.example.trainee_test.cryptolist.CryptoListViewModel
import com.example.trainee_test.databinding.FragmentUsdListBinding
import com.example.trainee_test.model.CryptoItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class USDFragment : Fragment(), CryptoAdapter.OnItemClickListener {
    private lateinit var binding: FragmentUsdListBinding
    private lateinit var cryptoAdapter: CryptoAdapter
    private val cryptoList = arrayListOf<CryptoItem>()
    private var repeatTimes = 3
    private val cryptoListViewModel: CryptoListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsdListBinding.inflate(layoutInflater)
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
        cryptoListViewModel.getAllCoins("1")
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
                            findNavController().navigate(R.id.action_USDFragment_to_errorFragment)
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

    override fun openCryptoDescription() {
        findNavController().navigate(R.id.action_USDFragment_to_descriptionFragment)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment USDFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            USDFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}