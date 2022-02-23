package com.igordam.todo.datasource

import com.igordam.todo.core.network.Resource
import com.igordam.todo.data.datasource.ToDoListDataSourceImpl
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
class ToDoListDataSourceTest {

    @get:Rule
    val coroutinesTestRule = CoroutineTestToDoRule()

    private val mock = Mocks()
    private val dataSource: ToDoListDataSourceImpl = mockk()
    
    @Test
    fun `getLists - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.getLists()
            } returns Resource.Success(mock.toDoList)
            val result = runCatching { dataSource.getLists() }
            coVerify { dataSource.getLists() }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `getListById - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.getListById(id = 1)
            } returns Resource.Success(mock.toDoListById)
            val result = runCatching { dataSource.getListById(id = 1) }
            coVerify { dataSource.getListById(id = 1) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `postLists - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.postLists(mock.postToDoRequest)
            } returns Resource.Success(mock.postToDoResponse)
            val result = runCatching { dataSource.postLists(mock.postToDoRequest) }
            coVerify { dataSource.postLists(mock.postToDoRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `putLists - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.putLists(id = 1, mock.postToDoRequest)
            } returns Resource.Success(mock.postToDoResponse)
            val result = runCatching { dataSource.putLists(id = 1, mock.postToDoRequest) }
            coVerify { dataSource.putLists(id = 1, mock.postToDoRequest) }
            assert(result.isSuccess)
        }
    }

    @Test
    fun `deleteLists - when returns success`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            coEvery {
                dataSource.deleteLists(id = 1)
            } returns Resource.Success(200)
            val result = runCatching { dataSource.deleteLists(id = 1) }
            coVerify { dataSource.deleteLists(id = 1) }
            assert(result.isSuccess)
        }
    }
    
}