package wwon.seokk.abandonedpets.domain.entity.abandonmentpublic

/**
 * Created by WonSeok on 2022.08.02
 **/
data class AbandonmentPublicEntity (
    val abandonmentPublicEntities: List<AbandonmentPublicResultEntity>
) {
    constructor(): this(arrayListOf())
}