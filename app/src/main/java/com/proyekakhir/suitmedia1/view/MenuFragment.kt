package com.proyekakhir.suitmedia1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.proyekakhir.core.data.source.local.UserEntity
import com.proyekakhir.core.domain.model.Events
import com.proyekakhir.suitmedia1.R
import com.proyekakhir.suitmedia1.databinding.FragmentMenuBinding
import com.proyekakhir.suitmedia1.utils.handleResult
import com.proyekakhir.suitmedia1.utils.navigateSafe
import com.proyekakhir.suitmedia1.utils.showToastShort


class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private var event: String = ""
    private var guest: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Menu"
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        val name = MenuFragmentArgs.fromBundle(requireArguments()).name


        with(binding) {
            tvName.text = name

            btnChooseEvent.setOnClickListener {
                findNavController().navigateSafe(R.id.action_menuFragment_to_eventFragment)
            }

            btnChooseGuest.setOnClickListener {
                findNavController().navigateSafe(R.id.action_menuFragment_to_guestFragment)
            }
        }

        findNavController().handleResult<Events>(
            viewLifecycleOwner,
            R.id.menuFragment,
            R.id.eventFragment
        ) { result ->
            event = result.title
            binding.btnChooseEvent.text = result.title
        }

        findNavController().handleResult<UserEntity>(
            viewLifecycleOwner,
            R.id.menuFragment,
            R.id.guestFragment
        ) { result ->
            guest = result.fullName.toString()
            binding.btnChooseGuest.text = result.fullName

            if (result.id!! % 2 == 0 && result.id!! % 3 == 0) {
                showToastShort(requireActivity(), "IOS")
            } else if (result.id!! % 2 == 0) {
                showToastShort(requireActivity(), "Blackberry")
            } else if (result.id!! % 3 == 0) {
                showToastShort(requireActivity(), "android")
            } else {
                showToastShort(requireActivity(), "feature Handphone")
            }
        }
    }

    override fun onResume() {
        requireActivity().title = "Menu"
        if (guest.isNotEmpty())
            binding.btnChooseGuest.text = guest
        if (event.isNotEmpty())
            binding.btnChooseEvent.text = event
        super.onResume()
    }
}