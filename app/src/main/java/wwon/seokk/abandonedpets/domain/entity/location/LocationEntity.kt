package wwon.seokk.abandonedpets.domain.entity.location

/**
 * Created by WonSeok on 2022.08.02
 **/
class LocationEntity(
    val locationEntities: List<LocationResultEntity>
) {
    constructor(): this(arrayListOf())
}