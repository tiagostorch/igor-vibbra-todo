package com.igordam.todo.di

import android.app.Application
import com.igordam.todo.data.datasource.*
import com.igordam.todo.data.repository.ToDoItemRepository
import com.igordam.todo.data.repository.ToDoItemRepositoryImpl
import com.igordam.todo.data.repository.ToDoListRepository
import com.igordam.todo.data.repository.ToDoListRepositoryImpl
import com.igordam.todo.data.service.ToDoApiService
import com.igordam.todo.viewModel.home.EditViewModel
import com.igordam.todo.viewModel.home.HomeViewModel
import com.igordam.todo.viewModel.item.ItemViewModel
import com.igordam.todo.usecase.ToDoItemUseCase
import com.igordam.todo.usecase.ToDoItemUseCaseImpl
import com.igordam.todo.usecase.ToDoListUseCase
import com.igordam.todo.usecase.ToDoListUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val serviceModule = module {
    single { ToDoApiService.create() }
}

val homeModule = module {
    single<ToDoListRepository> { ToDoListRepositoryImpl(get()) }
    single<ToDoListDataSource> { MockedToDoListDataSourceImpl() }

    viewModel { HomeViewModel(get(), Dispatchers.IO) }
    viewModel { EditViewModel(get(), Dispatchers.IO) }

    factory<ToDoListUseCase> { ToDoListUseCaseImpl(get()) }
}

val itemModule = module {
    single<ToDoItemRepository> { ToDoItemRepositoryImpl(get()) }
    single<ToDoItemDataSource> { MockedToDoItemDataSourceImpl() }

    viewModel { ItemViewModel(get(), Dispatchers.IO) }

    factory<ToDoItemUseCase> { ToDoItemUseCaseImpl(get()) }
}

class ApplicationModule : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationModule)
            modules(listOf(serviceModule, homeModule))
        }
    }
}