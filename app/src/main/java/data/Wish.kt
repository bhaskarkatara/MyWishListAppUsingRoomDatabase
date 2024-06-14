package data

data class Wish(
    val id: Long = 0L,
    val title : String,
    val description : String,
)

object dummyData{
    val wishList = listOf(
        Wish(1,"Title 1","Description 1"),
        Wish(2,"Title 2","Description 2"),
        Wish(3,"Title 3","Description 3"),
    )
}