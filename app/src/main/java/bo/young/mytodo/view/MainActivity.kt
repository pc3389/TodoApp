package bo.young.mytodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bo.young.mytodo.R
import bo.young.mytodo.model.Todo
import bo.young.mytodo.view.adapter.TodoListAdapter
import bo.young.mytodo.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var mTodoListAdapter: TodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initAddButton()
        initViewModel()
    }

    private fun initRecyclerView() {
        mTodoListAdapter = TodoListAdapter()
        rl_todo_list.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mTodoListAdapter
        }
    }

    private fun initAddButton() {
        btn_add_todo.setOnClickListener {
            openAddTodoDialog()
        }
    }

    private fun openAddTodoDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_todo, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Add")
            .setView(dialogView).setPositiveButton("OK") { _, _ ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()
                val createdDate = Date().time
                val todoModel = Todo(null, title, description, createdDate)
                mTodoViewModel.insertTodo(todoModel)
            }.setNegativeButton("Cancel", null).create()
        dialog.show()
    }

    private fun initViewModel() {
        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(TodoViewModel::class.java)
        mTodoViewModel.getTodoList().observe(this, {
            mTodoListAdapter.setTodoItems(it)
        })
    }
}