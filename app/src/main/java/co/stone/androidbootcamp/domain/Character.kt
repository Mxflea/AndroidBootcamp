package co.stone.androidbootcamp.domain


class Character (
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    )

enum class CharacterStatus(
    val status: String
) {
    Alive("alive"),
    Dead("dead"),
    Unknown("unknown");

    companion object{

        fun toStatus (status: String) =
            values().first{it.status.equals(status, ignoreCase = true)}
    }
}