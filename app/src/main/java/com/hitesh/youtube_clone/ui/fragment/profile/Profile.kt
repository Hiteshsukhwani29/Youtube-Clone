package com.hitesh.youtube_clone.ui.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hitesh.youtube_clone.databinding.FragmentExploreBinding
import com.hitesh.youtube_clone.databinding.FragmentProfileBinding
import com.hitesh.youtube_clone.repository.UserRepository

class Profile : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userRepository = UserRepository()

        val viewModelFactory = ProfileViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]

        return root
    }
}