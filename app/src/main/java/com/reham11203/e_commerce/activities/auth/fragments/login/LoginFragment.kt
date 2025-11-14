package com.reham11203.e_commerce.activities.auth.fragments.login

import android.content.Intent
import com.reham11203.e_commerce.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.reham11203.domain.utils.AppErrors
import com.reham11203.e_commerce.activities.home.HomeActivity
import com.reham11203.e_commerce.databinding.FragmentLoginBinding
import com.reham11203.e_commerce.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var _binding : FragmentLoginBinding? = null
    val binding get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        setupObservers()
    }

    private fun setupObservers() {
        loginViewModel.loginApiState.observe(viewLifecycleOwner) {
            showLoading(it is Resource.LoadingState)
            when(it){
                is Resource.ErrorState<*> -> showError(it.error)
                is Resource.SuccessState<*> -> startHomeActivity()
                else -> {}
            }
        }
    }

    private fun showRegistrationFragment(){

    }
    var dialog : ContentLoadingProgressBar? = null
    private fun showLoading(isLoading : Boolean){
        if (isLoading){
            dialog = ContentLoadingProgressBar(requireContext())
            dialog!!.show()
        }
        else{
            dialog?.hide()
        }

    }

    private fun showError(error : AppErrors){
        Toast.makeText(requireContext(), error.errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun startHomeActivity(){
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}