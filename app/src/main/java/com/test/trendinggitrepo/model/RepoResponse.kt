package com.test.trendinggitrepo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by alexgomes on 2018-02-05.
 */
class RepoResponse(val items: ArrayList<Repo>) {

    class Repo(
            @SerializedName("id") val id: Long,
            @SerializedName("name") val name: String,
            @SerializedName("full_name") val fullName: String,
            @SerializedName("description") val description: String,
            @SerializedName("html_url") val repoLink: String,
            @SerializedName("forks") val forks: Long,
            @SerializedName("watchers") val stars: Long,
            @SerializedName("open_issues") val openIssues: Long,
            @SerializedName("created_at") val createdAt: String,
            @SerializedName("updated_at") val updatedAt: String,
            @SerializedName("owner") val owner: Owner) : Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readLong(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readLong(),
                parcel.readLong(),
                parcel.readLong(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(Owner::class.java.classLoader)) {
        }

        class Owner(
                @SerializedName("login") val login: String,
                @SerializedName("avatar_url") val avatarUrl: String,
                @SerializedName("html_url") val htmlUrl: String) : Parcelable {
            constructor(parcel: Parcel) : this(
                    parcel.readString(),
                    parcel.readString(),
                    parcel.readString()) {
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(login)
                parcel.writeString(avatarUrl)
                parcel.writeString(htmlUrl)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<Owner> {
                override fun createFromParcel(parcel: Parcel): Owner {
                    return Owner(parcel)
                }

                override fun newArray(size: Int): Array<Owner?> {
                    return arrayOfNulls(size)
                }
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeLong(id)
            parcel.writeString(name)
            parcel.writeString(fullName)
            parcel.writeString(description)
            parcel.writeString(repoLink)
            parcel.writeLong(forks)
            parcel.writeLong(stars)
            parcel.writeLong(openIssues)
            parcel.writeString(createdAt)
            parcel.writeString(updatedAt)
            parcel.writeParcelable(owner, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Repo> {
            override fun createFromParcel(parcel: Parcel): Repo {
                return Repo(parcel)
            }

            override fun newArray(size: Int): Array<Repo?> {
                return arrayOfNulls(size)
            }
        }
    }
}




