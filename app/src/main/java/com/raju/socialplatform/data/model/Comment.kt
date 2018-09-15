package com.raju.socialplatform.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.raju.socialplatform.data.model.base.ListItem

@Entity
class Comment : Parcelable, ListItem {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var postId: Int = 0
    var name: String? = null
    var avatar: String? = null
    var comment: String? = null
    var modifiedOn: String? = null

    constructor() {}

    protected constructor(input: Parcel) {
        this.id = input.readInt()
        this.postId = input.readInt()
        this.name = input.readString()
        this.avatar = input.readString()
        this.comment = input.readString()
        this.modifiedOn = input.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeInt(this.postId)
        dest.writeString(this.name)
        dest.writeString(avatar)
        dest.writeString(this.comment)
        dest.writeString(modifiedOn)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Comment> = object : Parcelable.Creator<Comment> {
            override fun createFromParcel(source: Parcel): Comment {
                return Comment(source)
            }

            override fun newArray(size: Int): Array<Comment?> {
                return arrayOfNulls(size)
            }
        }
    }
}