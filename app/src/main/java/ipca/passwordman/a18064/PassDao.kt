package ipca.passwordman.a18064

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PassDao {

    @Query("SELECT * from pass_table ORDER BY pass ASC")
    fun getAlphabetizedWords(): LiveData<List<Password>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pass: Password)

    @Query("DELETE FROM pass_table")
    suspend fun deleteAll()

    @Delete
    fun delete(password: Password)
}