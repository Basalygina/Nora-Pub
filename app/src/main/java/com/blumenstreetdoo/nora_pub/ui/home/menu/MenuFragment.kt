package com.blumenstreetdoo.nora_pub.ui.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blumenstreetdoo.nora_pub.R
import com.blumenstreetdoo.nora_pub.databinding.FragmentMenuBinding
import com.bumptech.glide.Glide

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMenuImage()
    }

    private fun loadMenuImage() {
        Glide.with(this)
            .load(R.drawable.placeholder_menu_large) // Временное решение
            .into(binding.menuImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}