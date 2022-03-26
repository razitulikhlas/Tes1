package com.proyekakhir.suitmedia1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.proyekakhir.suitmedia1.databinding.FragmentHomeBinding
import com.proyekakhir.suitmedia1.utils.VariableGlobal
import com.proyekakhir.suitmedia1.utils.showToastShort


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Home"
        with(binding) {
            btnNext.setOnClickListener {
                if (edName.text.toString().isNotEmpty()) {
                    VariableGlobal.name = edName.text.toString()
                    val directions =
                        HomeFragmentDirections.actionHomeFragmentToMenuFragment(VariableGlobal.name)
                    findNavController().navigate(directions)
                } else {
                    showToastShort(requireActivity(), "Name required")
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onResume() {
        requireActivity().title = "Home"
        super.onResume()
    }


}