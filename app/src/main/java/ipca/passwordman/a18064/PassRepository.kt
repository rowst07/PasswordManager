package ipca.passwordman.a18064

import androidx.lifecycle.LiveData

class PassRepository(private val passDao: PassDao) {

    val allPass: LiveData<List<Password>> = passDao.getAlphabetizedWords()

    suspend fun insert(pass: Password) {
        passDao.insert(pass)
    }

    suspend fun delete(pass: Password) {
        passDao.delete(pass)

    }
}