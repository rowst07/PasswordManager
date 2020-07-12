package ipca.passwordman.a18064

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "pass_table")
class Password(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "pass") var password: String, var site: String, var description: String, var date: String)

interface PassDao {

    @Query("SELECT * from pass_table ORDER BY pass ASC")
    fun getAlphabetizedWords(): LiveData<List<Password>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pass: Password)

    @Query("DELETE FROM pass_table")
    suspend fun deleteAll()
}