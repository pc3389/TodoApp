package bo.young.mytodo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bo.young.mytodo.R
import bo.young.mytodo.model.Todo
import kotlinx.android.synthetic.main.item_todo.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodoListAdapter(val todoItems: ArrayList<Todo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title = view.tv_todo_title
        val description = view.tv_todo_description
        val createdDate = view.tv_todo_created_date
        fun bind(todoModel: Todo) {
            title.text = todoModel.title
            description.text = todoModel.description
            createdDate.text = convertLongToTime(todoModel.createdDate)
        }

        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.CANADA)
            return format.format(date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    fun addItem(todoModel: Todo) {
        todoItems.add(todoModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todoModel = todoItems[position]
        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(todoModel)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

}