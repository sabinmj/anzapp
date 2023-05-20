package com.sabin.anzapp.data.model

import com.google.gson.annotations.SerializedName

data class SpaceXLaunchesModel (

	@SerializedName("flight_number") val flight_number : Int,
	@SerializedName("mission_name") val mission_name : String,
	@SerializedName("mission_id") val mission_id : List<String>,
	@SerializedName("upcoming") val upcoming : Boolean,
	@SerializedName("launch_year") val launch_year : Int,
	@SerializedName("launch_date_unix") val launch_date_unix : Int,
	@SerializedName("launch_date_utc") val launch_date_utc : String,
	@SerializedName("launch_date_local") val launch_date_local : String,
	@SerializedName("is_tentative") val is_tentative : Boolean,
	@SerializedName("tentative_max_precision") val tentative_max_precision : String,
	@SerializedName("tbd") val tbd : Boolean,
	@SerializedName("launch_window") val launch_window : Int,
	@SerializedName("rocket") val rocket : Rocket,
	@SerializedName("ships") val ships : List<String>,
	@SerializedName("launch_site") val launch_site : LaunchSite,
	@SerializedName("launch_success") val launch_success : Boolean,
	@SerializedName("launch_failure_details") val launch_failure_details : LaunchFailureDetails,
	@SerializedName("links") val links : Links,
	@SerializedName("details") val details : String,
	@SerializedName("static_fire_date_utc") val static_fire_date_utc : String,
	@SerializedName("static_fire_date_unix") val static_fire_date_unix : Int,
	@SerializedName("timeline") val timeline : Timeline
)

data class Rocket (

	@SerializedName("rocket_id") val rocket_id : String,
	@SerializedName("rocket_name") val rocket_name : String,
	@SerializedName("rocket_type") val rocket_type : String
)
data class Links (

	@SerializedName("mission_patch") val mission_patch : String,
	@SerializedName("mission_patch_small") val mission_patch_small : String,
	@SerializedName("reddit_campaign") val reddit_campaign : String,
	@SerializedName("reddit_launch") val reddit_launch : String,
	@SerializedName("reddit_recovery") val reddit_recovery : String,
	@SerializedName("reddit_media") val reddit_media : String,
	@SerializedName("presskit") val presskit : String,
	@SerializedName("article_link") val article_link : String,
	@SerializedName("wikipedia") val wikipedia : String,
	@SerializedName("video_link") val video_link : String,
	@SerializedName("youtube_id") val youtube_id : String,
	@SerializedName("flickr_images") val flickr_images : List<String>
)

data class LaunchSite (

	@SerializedName("site_id") val site_id : String,
	@SerializedName("site_name") val site_name : String,
	@SerializedName("site_name_long") val site_name_long : String
)

data class LaunchFailureDetails (

	@SerializedName("time") val time : Int,
	@SerializedName("altitude") val altitude : String,
	@SerializedName("reason") val reason : String
)



data class Timeline (

	@SerializedName("webcast_liftoff") val webcast_liftoff : Int
)