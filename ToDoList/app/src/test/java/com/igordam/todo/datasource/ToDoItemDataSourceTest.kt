package com.igordam.todo.datasource

import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.datasource.ToDoItemDataSourceImpl
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
class ToDoItemDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestToDoRule()

    private val mock = Mocks()
    private val dataSource: ToDoItemDataSourceImpl = mockk()

    @Test
    fun `getToDoItems - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.getToDoItems(id = 1)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { dataSource.getToDoItems(id = 1) }
            coVerify { dataSource.getToDoItems(id = 1) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `postItem - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.postItem(id = 1, mock.postItemRequest)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { dataSource.postItem(id = 1, mock.postItemRequest) }
            coVerify { dataSource.postItem(id = 1, mock.postItemRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `putItem - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.putItem(id = 1, itemId = 2, mock.postItemRequest)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { dataSource.putItem(id = 1, itemId = 2, mock.postItemRequest) }
            coVerify { dataSource.putItem(id = 1, itemId = 2, mock.postItemRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `deleteItem - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.deleteItem(id = 1, itemId = 2)
            } returns Resource.Success(mock.itemList)
            val result = runCatching { dataSource.deleteItem(id = 1, itemId = 2) }
            coVerify { dataSource.deleteItem(id = 1, itemId = 2) }
            assert(result.isSuccess)
        }
    }

}