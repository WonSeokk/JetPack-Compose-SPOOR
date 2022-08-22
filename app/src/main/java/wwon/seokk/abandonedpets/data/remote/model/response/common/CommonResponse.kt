package wwon.seokk.abandonedpets.data.remote.model.response.common

import wwon.seokk.abandonedpets.data.remote.model.response.Item

/**
 * Created by WonSeok on 2022.08.02
 **/
data class CommonResponse(
    val response: Response
) {
    data class Response(
        val header: Header,
        val body: Body
    ) {
        data class Header(
            val reqNo: Int,
            val resultCode: String,
            val resultMsg: String
        )
        data class Body(
            val items: Items
        ) {
            data class Items(
                val item: List<Item>?
            )
        }
    }
}
