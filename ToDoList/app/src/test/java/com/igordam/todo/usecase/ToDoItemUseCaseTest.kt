package com.igordam.todo.usecase

import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.repository.ToDoItemRepository
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
class ToDoItemUseCaseTest {

    @get:Rule
    val corroutinesTestRule = CoroutineTestToDoRule()

    private val mock = Mocks()
    private val repository: ToDoItemRepository = mockk()
    private lateinit var useCase: ToDoItemUseCase

    @Before
    fun before() {
        useCase = ToDoItemUseCaseImpl(repository)
    }

    @Test
    fun `getToDoItems - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.getToDoItems(id = 1)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { useCase.getToDoItems(id = 1) }
            coVerify { useCase.getToDoItems(id = 1) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `postItem - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.postItem(id = 1, mock.postItemRequest)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { useCase.postItem(id = 1, mock.postItemRequest) }
            coVerify { useCase.postItem(id = 1, mock.postItemRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `putItem - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.putItem(id = 1, itemId = 2, mock.postItemRequest)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { useCase.putItem(id = 1, itemId = 2, mock.postItemRequest) }
            coVerify { useCase.putItem(id = 1, itemId = 2, mock.postItemRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `deleteItem - when returns success`() {
        corroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                useCase.deleteItem(id = 1, itemId = 2)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { useCase.deleteItem(id = 1, itemId = 2) }
            coVerify { useCase.deleteItem(id = 1, itemId = 2) }
            assert(result.isSuccess)
        }
    }

}