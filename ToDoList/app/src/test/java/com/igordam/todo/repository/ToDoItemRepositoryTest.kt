package com.igordam.todo.repository

import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.datasource.ToDoItemDataSourceImpl
import com.igordam.todo.data.repository.ToDoItemRepositoryImpl
import com.igordam.todo.mocks.Mocks
import com.igordam.todo.rule.CoroutineTestToDoRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ToDoItemRepositoryTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestToDoRule()

    private val mock = Mocks()
    private val dataSource: ToDoItemDataSourceImpl = mockk()
    private val repository: ToDoItemRepositoryImpl = ToDoItemRepositoryImpl(dataSource)

    @Test
    fun `getToDoItems - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.getToDoItems(id = 1)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { repository.getToDoItems(id = 1) }
            coVerify(exactly = 1) { dataSource.getToDoItems(id = 1) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `postItem - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.postItem(id = 1, mock.postItemRequest)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { repository.postItem(id = 1, mock.postItemRequest) }
            coVerify(exactly = 1) { dataSource.postItem(id = 1, mock.postItemRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `putItem - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.putItem(id = 1, itemId = 2, mock.postItemRequest)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { repository.putItem(id = 1, itemId = 2, mock.postItemRequest) }
            coVerify(exactly = 1) { dataSource.putItem(id = 1, itemId = 2, mock.postItemRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `deleteItem - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.deleteItem(id = 1, itemId = 2)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { repository.deleteItem(id = 1, itemId = 2) }
            coVerify(exactly = 1) { dataSource.deleteItem(id = 1, itemId = 2) }
            assert(result.isSuccess)
        }
    }

}