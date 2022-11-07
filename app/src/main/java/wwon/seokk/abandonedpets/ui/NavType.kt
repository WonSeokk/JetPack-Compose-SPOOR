package wwon.seokk.abandonedpets.ui

import android.os.Build.VERSION
import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import wwon.seokk.abandonedpets.data.remote.model.request.GetAbandonmentPublicRequest
import wwon.seokk.abandonedpets.domain.entity.abandonmentpublic.AbandonmentPublicResultEntity

/**
 * Created by WonSeok on 2022.08.21
 **/
class AbandonmentPublicRequestNavType : NavType<GetAbandonmentPublicRequest>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): GetAbandonmentPublicRequest? {
        return if(VERSION.SDK_INT >= 33) bundle.getParcelable(key, GetAbandonmentPublicRequest::class.java) else bundle.getParcelable(key)
    }

    override fun parseValue(value: String): GetAbandonmentPublicRequest {
        return Gson().fromJson(value, GetAbandonmentPublicRequest::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: GetAbandonmentPublicRequest) {
        bundle.putParcelable(key, value)
    }
}

class AbandonmentPublicResultNavType : NavType<AbandonmentPublicResultEntity>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): AbandonmentPublicResultEntity? {
        return if(VERSION.SDK_INT >= 33) bundle.getParcelable(key, AbandonmentPublicResultEntity::class.java) else bundle.getParcelable(key)
    }

    override fun parseValue(value: String): AbandonmentPublicResultEntity {
        return Gson().fromJson(value, AbandonmentPublicResultEntity::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: AbandonmentPublicResultEntity) {
        bundle.putParcelable(key, value)
    }
}