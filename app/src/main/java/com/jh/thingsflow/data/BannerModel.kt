package com.jh.thingsflow.data

import com.jh.thingsflow.base.BaseModel
import com.jh.thingsflow.base.ItemType

data class BannerModel(val url: String = "https://thingsflow.com/ko/home"): BaseModel() {
    override var modelType: ItemType = ItemType.Banner
}