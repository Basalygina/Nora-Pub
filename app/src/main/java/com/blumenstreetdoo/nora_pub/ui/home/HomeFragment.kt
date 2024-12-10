package com.blumenstreetdoo.nora_pub.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.blumenstreetdoo.nora_pub.databinding.FragmentHomeBinding
import com.blumenstreetdoo.nora_pub.domain.models.Event
import com.blumenstreetdoo.nora_pub.domain.models.News
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val eventsAdapter by lazy { EventsAdapter(mutableListOf()) { onEventClick(it) } }
    private val newsAdapter by lazy { NewsAdapter(mutableListOf()) { onNewsClick(it) } }
    val viewModel =
        ViewModelProvider(this).get(HomeViewModel::class.java)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.homeScreenState.collect { state ->
                when (state) {
                    is HomeScreenState.Content -> showContent(state.events, state.news)
                    is HomeScreenState.Error -> showError(noInternet = false)
                    HomeScreenState.Loading -> showLoading()
                    HomeScreenState.NoInternet -> showError(noInternet = true)
                }
            }
        }
        binding.rvEvents.adapter = eventsAdapter
        binding.rvNews.adapter = newsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading() {
        with(binding) {
            placeholder.visibility = View.GONE
            messageError.visibility = View.GONE
            buttonRetry.visibility = View.GONE
            rvEvents.visibility = View.INVISIBLE
            rvNews.visibility = View.INVISIBLE
            eventsProgressBar.visibility = View.VISIBLE
            newsProgressBar.visibility = View.VISIBLE
        }
    }

    private fun showContent(events: List<Event>, news: List<News>) {
        with(binding) {
            placeholder.visibility = View.GONE
            messageError.visibility = View.GONE
            buttonRetry.visibility = View.GONE
            rvEvents.visibility = View.VISIBLE
            rvNews.visibility = View.VISIBLE
            eventsProgressBar.visibility = View.GONE
            newsProgressBar.visibility = View.GONE
        }
    }

    private fun showError(noInternet: Boolean) {
        with(binding) {
            placeholder.visibility = View.VISIBLE
            messageError.visibility = View.VISIBLE
            buttonRetry.visibility = View.VISIBLE
            rvEvents.visibility = View.INVISIBLE
            rvNews.visibility = View.INVISIBLE
            eventsProgressBar.visibility = View.GONE
            newsProgressBar.visibility = View.GONE
        }
    }

    private fun onNewsClick(news: News) {
        TODO("Not yet implemented")
    }

    private fun onEventClick(event: Event) {
        TODO("Not yet implemented")
    }
}