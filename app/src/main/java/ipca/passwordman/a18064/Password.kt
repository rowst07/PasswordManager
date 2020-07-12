package ipca.passwordman.a18064

import androidx.lifecycle.LiveData
import androidx.room.*

@Entity(tableName = "pass_table")
data class Password(
    @PrimaryKey
    @ColumnInfo(name = "pass") var passwords: String)

