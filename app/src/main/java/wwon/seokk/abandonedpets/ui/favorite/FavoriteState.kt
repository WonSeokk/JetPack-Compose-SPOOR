package wwon.seokk.abandonedpets.ui.favorite

import wwon.seokk.abandonedpets.data.local.entities.Pet

/**
 * Created by WonSeok on 2022.11.07
 **/
data class FavoriteState (
    val favorites: List<Pet> = emptyList()
)