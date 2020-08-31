package odds.team.todolist.model

import android.app.Application
import io.reactivex.Observable

import kotlin.concurrent.thread


class LocalDataSource(application: Application) {
    private val taskDao: TaskDao
    val allTasks: Observable<List<Task>>

    init {
        val db = LocalDatabase.getInstance(application)
        taskDao = db.taskDao()
        allTasks = taskDao.all
    }

    fun insert(task: Task) {
        thread {
            taskDao.insert(task)
        }
    }

    fun update(task: Task) {
        thread {
            taskDao.update(task)
        }
    }
}