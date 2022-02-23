package com.igordam.todo.viewModel

import com.igordam.todo.mocks.Mocks
import com.igordam.todo.rule.CoroutineTestToDoRule
import com.igordam.todo.rule.InstantTaskExecutorRule
import com.igordam.todo.usecase.ToDoListUseCaseImpl
import com.igordam.todo.viewModel.home.EditViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EditViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestToDoRule = CoroutineTestToDoRule()

    private val mock = Mocks()
    private val dispatcher = TestCoroutineDispatcher()
    private val useCase: ToDoListUseCaseImpl = mockk()
    private val viewModel: EditViewModel = EditViewModel(useCase, dispatcher)

    @Test
    fun `editToDo - when execute exactly one time and returns success`() {
        coEvery { useCase.putLists(id = 1, mock.postToDoRequest) }
        viewModel.editToDo(id = 1, "Teste")
        coVerify(exactly = 1) { useCase.putLists(id = 1, mock.postToDoRequest) }
    }

    @Test
    fun `createToDo - when execute exactly one time and returns success`() {
        coEvery { useCase.postLists(mock.postToDoRequest) }
        viewModel.createToDo(id = 1, "Teste")
        coVerify(exactly = 1) { useCase.postLists(mock.postToDoRequest) }
    }

}