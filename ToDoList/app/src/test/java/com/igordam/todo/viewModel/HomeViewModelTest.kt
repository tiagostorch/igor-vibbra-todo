package com.igordam.todo.viewModel

import com.igordam.todo.rule.CoroutineTestToDoRule
import com.igordam.todo.rule.InstantTaskExecutorRule
import com.igordam.todo.usecase.ToDoListUseCaseImpl
import com.igordam.todo.viewModel.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestToDoRule = CoroutineTestToDoRule()

    private val dispatcher = TestCoroutineDispatcher()
    private val useCase: ToDoListUseCaseImpl = mockk()
    private val viewModel: HomeViewModel = HomeViewModel(useCase, dispatcher)

    @Test
    fun `getToDoLists - when execute exactly one time and returns success`() {
        coEvery { useCase.getLists() }
        viewModel.getToDoLists()
        coVerify(exactly = 1) { useCase.getLists() }
    }

    @Test
    fun `deleteToDo - when execute exactly one time and returns success`() {
        coEvery { useCase.deleteLists(id = 1) }
        viewModel.deleteToDo(id = 1)
        coVerify(exactly = 1) { useCase.deleteLists(id = 1) }
    }

}