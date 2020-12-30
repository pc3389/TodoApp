package bo.young.mytodo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import bo.young.mytodo.database.TodoDao
import bo.young.mytodo.database.TodoDatabase
import bo.young.mytodo.model.Todo

class TodoRepository(application: Application) {
    private var mTodoDatabase: TodoDatabase
    private var mTodoDAO: TodoDao
    private var mTodoItems: LiveData<List<Todo>>

    init {
        mTodoDatabase = TodoDatabase.getInstance(application)
        mTodoDAO = mTodoDatabase.todoDao()
        mTodoItems = mTodoDAO.getTodoList()
    }

    fun getTodoList(): LiveData<List<Todo>> {
        return mTodoItems
    }

    fun insertTodo(todoModel: Todo) {
        Thread(Runnable {
            mTodoDAO.insertTodo(todoModel)
        }).start()
    }

}