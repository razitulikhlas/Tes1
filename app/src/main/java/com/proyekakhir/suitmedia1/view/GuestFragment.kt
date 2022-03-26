package com.proyekakhir.suitmedia1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.proyekakhir.core.adapter.GuestAdapter
import com.proyekakhir.core.data.source.Resource
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.suitmedia1.databinding.FragmentGuestBinding
import com.proyekakhir.suitmedia1.utils.finishWithResult
import com.proyekakhir.suitmedia1.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GuestFragment : Fragment(), GuestAdapter.GuestCallback {

    private var _binding: FragmentGuestBinding? = null
    private val binding get() = _binding!!
    private var adapter: GuestAdapter? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGuestBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        requireActivity().title = "Guest"
        adapter = GuestAdapter()
        adapter?.setListener(this)


        binding.run {
            swipeRefresh.setOnRefreshListener { getData() }
        }
        getData()


        viewModel.users.observe(viewLifecycleOwner) { resource ->
            if (resource != null) {
                when (resource) {
                    is Resource.Success -> {
                        showData()
                        adapter?.setData(resource.data!!)
                        with(binding) {
                            rcv.layoutManager = GridLayoutManager(requireActivity(), 2)
                            rcv.adapter = adapter
                        }
                    }
                    is Resource.Loading -> showLoading()
                    is Resource.Error -> {
                        showData()
                    }
                }
            }

        }


    }

    private fun getData() {
        lifecycleScope.launch {
            showLoading()
            delay(500)
            viewModel.getUsers()
        }
    }

    private fun showLoading() {
        with(binding) {
            rcv.visibility = View.GONE
            shimmers.visibility = View.VISIBLE
            shimmers.startShimmer()
        }
    }


    private fun showData() {
        with(binding) {
            swipeRefresh.isRefreshing = false
            rcv.visibility = View.VISIBLE
            shimmers.stopShimmer()
            shimmers.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        binding.rcv.adapter = null
        adapter = null
        _binding = null
        super.onDestroyView()
    }

    override fun onClick(guest: UserEntity) {
        findNavController().finishWithResult(guest)
    }


}