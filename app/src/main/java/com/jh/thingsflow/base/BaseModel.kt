package com.jh.thingsflow.base

abstract class BaseModel {
    abstract var modelType: ItemType
}

sealed class ItemType {
    object Issue: ItemType()
    object Banner: ItemType()
}
