package ipca.passwordman.a18064

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Password::class], version = 1, exportSchema = false)
public abstract class PassRoomDatabase : RoomDatabase() {

    abstract fun passDao(): PassDao

    private class PasswordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.passDao())
                }
            }
        }

        suspend fun populateDatabase(passDao: PassDao) {
            passDao.deleteAll()//Apaga todos os conteúdos da base de dados

            // Adiciona palavras-passe teste
            var password = Password("Bom") //Criação de uma palavra passe
            passDao.insert(password) //Inserção na base de dados
            password = Password("Diaaaaaaaaaaaaaaaaaaaa")
            passDao.insert(password)

        }
    }

    companion object {

        @Volatile
        private var INSTANCE: PassRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PassRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassRoomDatabase::class.java,
                    "pass_database"
                )
                    .addCallback(PasswordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}