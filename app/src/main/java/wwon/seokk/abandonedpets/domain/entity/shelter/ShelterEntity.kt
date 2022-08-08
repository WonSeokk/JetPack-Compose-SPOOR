package wwon.seokk.abandonedpets.domain.entity.shelter

/**
 * Created by WonSeok on 2022.08.02
 **/
data class ShelterEntity (
    val shelterEntities: List<ShelterResultEntity>
) {
    constructor(): this(arrayListOf())
}