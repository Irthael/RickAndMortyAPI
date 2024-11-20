package com.dani.rickandmortyapi.ui.list

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dani.domain.Mycharacter
import com.dani.rickandmortyapi.ApiApplication
import com.dani.rickandmortyapi.databinding.ActivityCharactersListBinding
import com.dani.rickandmortyapi.ui.common.BaseFragment
import com.dani.rickandmortyapi.utils.getViewModel

class MainFragment : BaseFragment() {

    private lateinit var navController: NavController
    private lateinit var component: CharactersListActivityComponent
    private val viewModel by lazy { getViewModel {component.viewModel }}

    private lateinit var adapter: MainAdapter

    private lateinit var binding: ActivityCharactersListBinding
    private var characterList = mutableListOf<Mycharacter>()
    private var name: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View {
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        component = (activity?.application as ApiApplication).charactersComponent.plus(CharactersListActivityModule())

        binding.rvItems.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        adapter = MainAdapter(characterList, viewModel::onItemClicked, ::reloadList)
        binding.rvItems.adapter = adapter

        binding.btnSearch.setOnClickListener {
            intPage = 0
            name = binding.editText.text.toString()
            viewModel.getCharacters(intPage, name)
        }

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private var intPage = 0
    private fun reloadList(){
        intPage++
        viewModel.getCharacters(intPage, name)
    }

    override fun loadData() {
        viewModel.getCharacters(intPage, name)
    }

    /*
    override fun onResume() {
        super.onResume()
        viewModel.getCharacters(intPage, name)
    }
    */

    private fun updateUi(uiModel: MainViewModel.UiModel) {

        binding.itemLoading.loading.visibility = if (uiModel is MainViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when (uiModel) {
            is MainViewModel.UiModel.Content -> {
                characterList.clear()
                characterList.addAll(uiModel.items)
                adapter.notifyDataSetChanged()
            }
            is MainViewModel.UiModel.Navigation -> {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(uiModel.item.id)
                navController.navigate(action)
            }

            MainViewModel.UiModel.Loading -> {}
        }
    }
}