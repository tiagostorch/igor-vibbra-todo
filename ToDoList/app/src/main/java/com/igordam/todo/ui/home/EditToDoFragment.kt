package com.igordam.todo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.igordam.todo.R
import com.igordam.todo.databinding.FragmentEditToDoBinding
import com.igordam.todo.di.homeModule
import com.igordam.todo.viewModel.home.EditViewModel
import com.igordam.todo.ui.home.HomeFragment.Companion.DEFAULT_ID
import com.igordam.todo.ui.home.HomeFragment.Companion.DEFAULT_NAME
import com.igordam.todo.util.DialogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class EditToDoFragment : Fragment() {

    private val modules by lazy {
        unloadKoinModules(homeModule)
        loadKoinModules(homeModule)
    }

    private fun injectModules() = modules
    private val editViewModel: EditViewModel by viewModel()
    private lateinit var binding: FragmentEditToDoBinding
    private lateinit var loadingDialog: AlertDialog

    private val args: EditToDoFragmentArgs by navArgs()
    private var isEdition = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditToDoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectModules()
        setUpClickListeners()
        setUpObservers()
        setArgs()
    }

    private fun setArgs() {
        if (args.id == DEFAULT_ID || args.name == DEFAULT_NAME) isEdition = false
        else {
            binding.editIdTodo.setText(args.id.toString())
            binding.editNameTodo.setText(args.name)
            binding.toolbarItem.title = args.name
        }
    }

    private fun setUpClickListeners() {
        binding.imageButtonSave.setOnClickListener {
            val newName = binding.editNameTodo.text.toString()
            if (newName.isNotEmpty()) requestSave(newName)
        }
        binding.toolbarItem.setNavigationOnClickListener { view ->
            Navigation.findNavController(view).popBackStack()
        }
    }

    private fun requestSave(newName: String) {
        if (isEdition) editViewModel.editToDo(args.id, newName)
        else editViewModel.createToDo(args.id, newName)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObservers() {
        editViewModel.loading.observe(viewLifecycleOwner, { show ->
            context?.let { ctx ->
                if (show) loadingDialog = DialogUtil.showLadingDialog(ctx)
                else loadingDialog.dismiss()
            }
        })
        editViewModel.success.observe(viewLifecycleOwner, { show ->
            if (show) Snackbar.make(
                binding.editLayout,
                getString(R.string.edit_save_message),
                Snackbar.LENGTH_LONG
            ).show()
        })
        editViewModel.error.observe(viewLifecycleOwner, { message ->
            Snackbar.make(
                binding.editLayout,
                message,
                Snackbar.LENGTH_LONG
            ).show()
        })
    }
}