import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "wish-title")
    val title : String = "",

    @ColomnInfo(name = "wish-desc")
    val description : String =""
)

object dummyData{
    val wishList = listOf(
        Wish(1, "Title 1", "Description 1"),
        Wish(2, "Title 2", "Description 2"),
        Wish(3, "Title 3", "Description 3"),
    )
}