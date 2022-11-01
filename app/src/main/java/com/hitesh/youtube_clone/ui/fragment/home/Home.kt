package com.hitesh.youtube_clone.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hitesh.youtube_clone.databinding.FragmentHomeBinding
import com.hitesh.youtube_clone.repository.UserRepository

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userRepository = UserRepository()

        val viewModelFactory = HomeViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        return root
    }
}