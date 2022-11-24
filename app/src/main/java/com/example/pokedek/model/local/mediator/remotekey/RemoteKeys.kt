package com.example.pokedek.model.local.mediator.remotekey

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeysPokemon")
data class RemoteKeys (
    @PrimaryKey
    val id : String,
    val prevKey : Int?,
    val nextKey : Int?
)