package com.jh.thingsflow.base

import com.jh.thingsflow.data.remote.response.User

//abstract class BaseModel {
//    abstract var modelType: ItemType
//}

sealed class ItemType {
    object Issue: ItemType()
    object Banner: ItemType()
}

sealed class BaseModel {
    data class ResultIssueItem(
        val body: String,
        val id: Int,
        val number: Int,
        val title: String,
        val user: User
    ): BaseModel()
    data class BannerModel(val url: String = "https://thingsflow.com/ko/home",
                           val image_url: String = "https://s3.ap-northeast-2.amazonaws.com/hellobot-kr-test/image/main_logo.png"): BaseModel()
}
