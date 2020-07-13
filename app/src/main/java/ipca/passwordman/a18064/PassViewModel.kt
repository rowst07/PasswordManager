package ipca.passwordman.a18064

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PassViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PassRepository

    val allPass: LiveData<List<Password>>

    init {
        val wordsDao = PassRoomDatabase.getDatabase(application, viewModelScope).passDao()
        repository = PassRepository(wordsDao)
        allPass = repository.allPass
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(pass: Password) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(pass)
    }

    fun delete(pass: Password) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(pass)
    }
}