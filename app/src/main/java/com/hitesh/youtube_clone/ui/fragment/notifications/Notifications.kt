package com.hitesh.youtube_clone.ui.fragment.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hitesh.youtube_clone.databinding.FragmentExploreBinding
import com.hitesh.youtube_clone.databinding.FragmentNotificationsBinding
import com.hitesh.youtube_clone.repository.UserRepository
import com.hitesh.youtube_clone.ui.fragment.explore.NotificationsViewModel

class Notifications : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val userRepository = UserRepository()

        val viewModelFactory = NotificationsViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[NotificationsViewModel::class.java]

        return root
    }
}