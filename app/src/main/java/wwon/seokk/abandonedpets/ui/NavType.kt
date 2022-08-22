package wwon.seokk.abandonedpets.ui

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest

/**
 * Created by WonSeok on 2022.08.21
 **/
class AbandonmentPublicRequestNavType : NavType<GetAbandonmentPublicRequest>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): GetAbandonmentPublicRequest? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): GetAbandonmentPublicRequest {
        return Gson().fromJson(value, GetAbandonmentPublicRequest::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: GetAbandonmentPublicRequest) {
        bundle.putParcelable(key, value)
    }
}