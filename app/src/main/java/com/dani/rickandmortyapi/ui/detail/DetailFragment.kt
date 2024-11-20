package com.dani.rickandmortyapi.ui.detail

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.dani.rickandmortyapi.ApiApplication
import com.dani.rickandmortyapi.databinding.ActivityCharactersDetailTwoBinding
import com.dani.rickandmortyapi.ui.common.BaseFragment
import com.dani.rickandmortyapi.utils.getViewModel

class DetailFragment : BaseFragment() {

    private lateinit var component: CharactersDetailComponent
    private val viewModel by lazy { getViewModel {component.viewModel }}

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: ActivityCharactersDetailTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityCharactersDetailTwoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component = (activity?.application as ApiApplication).charactersComponent.plus(
            CharactersDetailModule().apply { itemId = args.id }
        )

        binding.item = viewModel
        binding.lifecycleOwner = this
    }
}
