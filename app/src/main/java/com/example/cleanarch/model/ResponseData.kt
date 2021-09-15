package com.example.cleanarch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResponseData(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String
)

data class Source(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String? = null
)

data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String,

	@field:SerializedName("author")
	val author: String? = "",

	@field:SerializedName("urlToImage")
	val urlToImage: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("source")
	val source: Source,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("content")
	val content: String
)

data class NewsData(
	val publishedAt: String? = "",
	val author: String? = "",
	val urlToImage: String? = "",
	val description: String? = "",
	val title: String? = ""
):Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(publishedAt)
		parcel.writeString(author)
		parcel.writeString(urlToImage)
		parcel.writeString(description)
		parcel.writeString(title)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<NewsData> {
		override fun createFromParcel(parcel: Parcel): NewsData {
			return NewsData(parcel)
		}

		override fun newArray(size: Int): Array<NewsData?> {
			return arrayOfNulls(size)
		}
	}
}