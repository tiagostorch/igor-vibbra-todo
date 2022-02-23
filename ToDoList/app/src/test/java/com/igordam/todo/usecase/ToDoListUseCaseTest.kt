package com.igordam.todo.usecase

import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.repository.ToDoListRepository
import com.igordam.todo.mocks.Mocks
import com.igordam.todo.rule.CoroutineTestToDoRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ToDoListUseCaseTest {

    @get:Rule
    val corroutinesTestRule = CoroutineTestToDoRule()

    private val mock = Mocks()
    private val repository: ToDoListRepository = mockk()
    private lateinit var useCase: ToDoListUseCase

    @Before
    fun before() {
        useCase = ToDoListUseCaseImpl(repository)
    }

    @Test
    fun `getLists - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.getLists()
            } returns Resource.Success(mock.toDoList)
            val result = runCatching { useCase.getLists() }
            coVerify { useCase.getLists() }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `getListById - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.getListById(id = 1)
            } returns Resource.Success(mock.toDoListById)
            val result = runCatching { useCase.getListById(id = 1) }
            coVerify { useCase.getListById(id = 1) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `postLists - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.postLists(mock.postToDoRequest)
            } returns Resource.Success(mock.postToDoResponse)
            val result = runCatching { useCase.postLists(mock.postToDoRequest) }
            coVerify { useCase.postLists(mock.postToDoRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `putLists - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.putLists(id = 1, mock.postToDoRequest)
            } returns Resource.Success(mock.postToDoResponse)
            val result = runCatching { useCase.putLists(id = 1, mock.postToDoRequest) }
            coVerify { useCase.putLists(id = 1, mock.postToDoRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `deleteLists - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.deleteLists(id = 1)
            } returns Resource.Success(200)
            val result = runCatching { useCase.deleteLists(id = 1) }
            coVerify { useCase.deleteLists(id = 1) }
            assert(result.isSuccess)
        }
    }

}