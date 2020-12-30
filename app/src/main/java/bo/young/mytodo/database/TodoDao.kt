package bo.young.mytodo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import bo.young.mytodo.model.Todo

@Dao
interface TodoDao {

    @Query("SELECT * from Todo ORDER BY createdDate ASC")
    fun getTodoList(): LiveData<List<Todo>>

    @Insert
    fun insertTodo(todoModel: Todo)
}