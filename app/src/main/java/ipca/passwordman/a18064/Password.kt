package ipca.passwordman.a18064

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "pass_table")
data class Password(
    @PrimaryKey
    @ColumnInfo(name = "pass") var passwords: String, var site: String, var description: String, var date: String)

interface PassDao {

    @Query("SELECT * from pass_table ORDER BY pass ASC")
    fun getAlphabetizedWords(): LiveData<List<Password>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pass: Password)

    @Query("DELETE FROM pass_table")
    suspend fun deleteAll()
}