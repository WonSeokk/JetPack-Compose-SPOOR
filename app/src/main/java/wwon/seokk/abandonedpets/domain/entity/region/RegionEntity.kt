package wwon.seokk.abandonedpets.domain.entity.region

/**
 * Created by WonSeok on 2022.08.02
 **/
class RegionEntity(
    val regionEntities: List<RegionResultEntity>
) {
    constructor(): this(arrayListOf())
}