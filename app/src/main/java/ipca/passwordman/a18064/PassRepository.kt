package ipca.passwordman.a18064

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class PassRepository(private val passDao: PassDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPass: LiveData<List<Password>> = passDao.getAlphabetizedWords()

    suspend fun insert(pass: Password) {
        passDao.insert(pass)
    }
}