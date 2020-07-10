package ipca.passwordman.a18064

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pass_table")
class Password(@PrimaryKey @ColumnInfo(name = "pass") val password: String)