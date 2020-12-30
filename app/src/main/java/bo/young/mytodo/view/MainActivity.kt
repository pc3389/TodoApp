package bo.young.mytodo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import bo.young.mytodo.R
import bo.young.mytodo.model.Todo
import bo.young.mytodo.view.adapter.TodoListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_todo.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mTodoListAdater: TodoListAdapter
    private val mTodoItems: ArrayList<Todo> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTodoItems.run {
            add(Todo("안드로이드 포스팅1", "Kotlin + MVVM + AAC 로 Todo 앱 만들기 - 1", Date().time))
            add(Todo("안드로이드 포스팅2", "Kotlin + MVVM + AAC 로 Todo 앱 만들기 - 2", Date().time))
            add(Todo("안드로이드 포스팅3", "Kotlin + MVVM + AAC 로 Todo 앱 만들기 - 3", Date().time))
        }
        initRecyclerView()
        initAddButton()
    }

    private fun initRecyclerView() {
        mTodoListAdater = TodoListAdapter(mTodoItems)
        rl_todo_list.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mTodoListAdater
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
            .setTitle("추가하기")
            .setView(dialogView).setPositiveButton("확인") { _, _ ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()
                val createdDate = Date().time
                val todoModel = Todo(
                    title,
                    description,
                    createdDate
                )
                mTodoListAdater.addItem(todoModel)
                mTodoListAdater.notifyDataSetChanged()
            }.setNegativeButton("취소", null).create()
        dialog.show()
    }

}