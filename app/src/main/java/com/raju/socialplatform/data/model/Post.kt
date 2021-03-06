package com.raju.socialplatform.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.raju.socialplatform.data.model.base.ListItem

@Entity
public class Post : Parcelable, ListItem {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var name: String? = null
    var avatar: String? = null
    var message: String? = null
    var description: String? = null
    var url: String? = null
    var modifiedOn: String? = null

    constructor() {}

    protected constructor(input: Parcel) {
        this.id = input.readInt()
        this.name = input.readString()
        this.avatar = input.readString()
        this.message = input.readString()
        this.description = input.readString()
        this.url = input.readString()
        this.modifiedOn = input.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.name)
        dest.writeString(this.avatar)
        dest.writeString(this.message)
        dest.writeString(this.description)
        dest.writeString(this.url)
        dest.writeString(modifiedOn)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Post> = object : Parcelable.Creator<Post> {
            override fun createFromParcel(source: Parcel): Post {
                return Post(source)
            }

            override fun newArray(size: Int): Array<Post?> {
                return arrayOfNulls(size)
            }
        }
    }
}