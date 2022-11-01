package com.hitesh.youtube_clone.ui.fragment.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hitesh.youtube_clone.databinding.FragmentExploreBinding
import com.hitesh.youtube_clone.repository.UserRepository

class Explore : Fragment() {
    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userRepository = UserRepository()

        val viewModelFactory = ExploreViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ExploreViewModel::class.java]

        return root
    }
}