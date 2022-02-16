package com.jh.thingsflow.data.remote.response

import com.jh.thingsflow.base.BaseModel

class ResultIssue: ArrayList<BaseModel.ResultIssueItem>()

//data class ResultIssueItem(
//    val body: String,
//    val id: Int,
//    val number: Int,
//    val title: String,
//    val user: User
//): BaseModel() {
//    override var modelType: ItemType = ItemType.Issue
//}

data class User(
    val avatar_url: String,
    val id: Int,
    val login: String
)