package com.orioninc.techclub.acchelloworld

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "token") val token: String
)
