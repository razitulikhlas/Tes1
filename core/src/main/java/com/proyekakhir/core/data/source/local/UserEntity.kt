package com.proyekakhir.core.data.source.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tbl_user")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "avatar")
    val avatar: String? = null,
    @ColumnInfo(name = "fullName")
    val fullName: String? = null,
    @ColumnInfo(name = "email")
    val email: String? = null
):Parcelable