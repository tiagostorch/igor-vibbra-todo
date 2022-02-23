package com.igordam.todo.viewModel

import com.igordam.todo.rule.CoroutineTestToDoRule
import com.igordam.todo.rule.InstantTaskExecutorRule
import com.igordam.todo.usecase.ToDoItemUseCaseImpl
import com.igordam.todo.usecase.ToDoListUseCaseImpl
import com.igordam.todo.viewModel.home.HomeViewModel
import com.igordam.todo.viewModel.item.ItemViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestToDoRule = CoroutineTestToDoRule()

    private val dispatcher = TestCoroutineDispatcher()
    private val useCase: ToDoItemUseCaseImpl = mockk()
    private val viewModel: ItemViewModel = ItemViewModel(useCase, dispatcher)

    @Test
    fun `getItemsList - when execute exactly one time and returns success`() {
        coEvery { useCase.getToDoItems(id = 1) }
        viewModel.getItemsList(id = 1)
        coVerify(exactly = 1) { useCase.getToDoItems(id = 1) }
    }

}