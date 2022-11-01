package com.hitesh.youtube_clone.ui.fragment.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hitesh.youtube_clone.databinding.FragmentLibraryBinding
import com.hitesh.youtube_clone.repository.UserRepository

class Library : Fragment() {
    private var _binding: FragmentLibraryBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LibraryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userRepository = UserRepository()

        val viewModelFactory = LibraryViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LibraryViewModel::class.java]

        return root
    }
}