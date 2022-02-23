package com.igordam.todo.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.igordam.todo.databinding.FragmentHomeBinding
import com.igordam.todo.di.homeModule
import com.igordam.todo.viewModel.home.HomeViewModel
import com.igordam.todo.util.DialogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class HomeFragment : Fragment() {

    private val modules by lazy {
        unloadKoinModules(homeModule)
        loadKoinModules(homeModule)
    }

    private fun injectModules() = modules
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var toDoAdapter: ToDoAdapter
    private lateinit var loadingDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectModules()
        context?.let { ctx -> setUpAdapters(ctx) }
        setUpObservers()
        setUpListeners()
    }

    override fun onStart() {
        super.onStart()
        homeViewModel.getToDoLists()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObservers() {
        homeViewModel.toDoList.observe(viewLifecycleOwner, { list ->
            toDoAdapter.setDataList(list)
            toDoAdapter.notifyDataSetChanged()
        })
        homeViewModel.loading.observe(viewLifecycleOwner, { show ->
            context?.let { ctx ->
                if (show) loadingDialog = DialogUtil.showLadingDialog(ctx)
                else loadingDialog.dismiss()
            }
        })
        homeViewModel.error.observe(viewLifecycleOwner, { message ->
            Snackbar.make(
                binding.homeLayout,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        })
    }

    private fun setUpListeners() {
        binding.imageButtonAdd.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeFragmentToHomeEdit(DEFAULT_ID, DEFAULT_NAME)
            this.findNavController().navigate(action)
        }
    }

    private fun setUpAdapters(context: Context) {
        binding.recyclerViewPhotosHome.layoutManager =
            LinearLayoutManager(context)
        toDoAdapter = ToDoAdapter(::goToItems, ::goToEdit, ::delete)
        binding.recyclerViewPhotosHome.adapter = toDoAdapter
    }

    private fun goToItems(id: Int) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToItemList(id)
        this.findNavController().navigate(action)
    }

    private fun goToEdit(id: Int, name: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToHomeEdit(id, name)
        this.findNavController().navigate(action)
    }

    private fun delete(id: Int) = homeViewModel.deleteToDo(id)

    companion object {
        internal const val DEFAULT_ID = 0
        internal const val DEFAULT_NAME = " "
    }
}