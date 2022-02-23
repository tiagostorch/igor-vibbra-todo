package com.igordam.todo.ui.item

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.igordam.todo.databinding.FragmentItemBinding
import com.igordam.todo.di.itemModule
import com.igordam.todo.viewModel.item.ItemViewModel
import com.igordam.todo.ui.home.EditToDoFragmentArgs
import com.igordam.todo.util.DialogUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ItemFragment : Fragment() {

    private val modules by lazy {
        unloadKoinModules(itemModule)
        loadKoinModules(itemModule)
    }

    private fun injectModules() = modules
    private val itemViewModel: ItemViewModel by viewModel()
    private lateinit var binding: FragmentItemBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var loadingDialog: AlertDialog

    private val args: EditToDoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)
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
        itemViewModel.getItemsList(args.id)
    }

    private fun setUpListeners() {
        binding.toolbarItem.setNavigationOnClickListener { view ->
            Navigation.findNavController(view).popBackStack()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObservers() {
        itemViewModel.loading.observe(viewLifecycleOwner, { show ->
            context?.let { ctx ->
                if (show) loadingDialog = DialogUtil.showLadingDialog(ctx)
                else loadingDialog.dismiss()
            }
        })
        itemViewModel.itemsList.observe(viewLifecycleOwner, { list ->
            itemAdapter.setDataList(list)
            itemAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpAdapters(context: Context) {
        binding.recyclerViewItem.layoutManager =
            LinearLayoutManager(context)
        itemAdapter = ItemAdapter()
        binding.recyclerViewItem.adapter = itemAdapter
    }
}