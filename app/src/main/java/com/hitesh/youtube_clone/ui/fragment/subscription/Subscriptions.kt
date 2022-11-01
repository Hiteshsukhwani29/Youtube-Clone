package com.hitesh.youtube_clone.ui.fragment.subscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hitesh.youtube_clone.databinding.FragmentExploreBinding
import com.hitesh.youtube_clone.databinding.FragmentSubscriptionsBinding
import com.hitesh.youtube_clone.repository.UserRepository

class Subscriptions : Fragment() {
    private var _binding: FragmentSubscriptionsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: SubscriptionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userRepository = UserRepository()

        val viewModelFactory = SubscriptionsViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[SubscriptionsViewModel::class.java]

        return root
    }
}