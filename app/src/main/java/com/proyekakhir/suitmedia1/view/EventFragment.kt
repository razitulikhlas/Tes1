package com.proyekakhir.suitmedia1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.core.adapter.EventAdapter
import com.proyekakhir.core.domain.model.Events
import com.proyekakhir.suitmedia1.databinding.FragmentEventBinding
import com.proyekakhir.suitmedia1.utils.finishWithResult
import com.proyekakhir.suitmedia1.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : Fragment(), EventAdapter.EventCallback {

    private var _binding: FragmentEventBinding? = null
    private val binding get() = _binding!!
    private var adapter: EventAdapter? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        requireActivity().title = "Events"
        adapter = EventAdapter()
        adapter?.setListener(this)
        binding.rcv.layoutManager = LinearLayoutManager(requireActivity())
        viewModel.event.observe(viewLifecycleOwner) {
            with(binding) {
                adapter?.setData(it)
                rcv.adapter = adapter
            }
        }


    }

    override fun onDestroyView() {
        binding.rcv.adapter = null
        _binding = null
        adapter = null
        super.onDestroyView()
    }

    override fun onClick(event: Events) {
        findNavController().finishWithResult(event)
    }


}