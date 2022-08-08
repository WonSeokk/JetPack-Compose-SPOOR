package wwon.seokk.abandonedpets.domain.entity.kind

/**
 * Created by WonSeok on 2022.08.02
 **/
data class KindEntity (
    val kindEntities: List<KindResultEntity>
) {
    constructor(): this(arrayListOf())
}