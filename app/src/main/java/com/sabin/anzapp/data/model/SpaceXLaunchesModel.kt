package com.sabin.anzapp.data.model

import com.google.gson.annotations.SerializedName

data class SpaceXLaunchesModel(

	@SerializedName("mission_name") val mission_name: String,
	@SerializedName("launch_date_local") val launch_date_local: String,
	@SerializedName("rocket") val rocket: Rocket,
	@SerializedName("launch_site") val launch_site: LaunchSite,
	@SerializedName("launch_success") val launch_success: Boolean,
	@SerializedName("launch_failure_details") val launch_failure_details: LaunchFailureDetails? = null,
	@SerializedName("links") val links: Links? = null,
	@SerializedName("details") val details: String,
	@SerializedName("timeline") val timeline: Timeline? = null
)

data class Rocket(
	@SerializedName("rocket_name") val rocket_name: String,
)

data class Links(

	@SerializedName("mission_patch") val mission_patch: String,
	@SerializedName("mission_patch_small") val mission_patch_small: String,
	@SerializedName("reddit_campaign") val reddit_campaign: String,
	@SerializedName("reddit_launch") val reddit_launch: String,
	@SerializedName("reddit_recovery") val reddit_recovery: String,
	@SerializedName("reddit_media") val reddit_media: String,
	@SerializedName("presskit") val presskit: String,
	@SerializedName("article_link") val article_link: String,
	@SerializedName("wikipedia") val wikipedia: String,
	@SerializedName("video_link") val video_link: String,
	@SerializedName("youtube_id") val youtube_id: String,
	@SerializedName("flickr_images") val flickr_images: List<String>
)

data class LaunchSite(
	@SerializedName("site_name") val site_name: String,
	@SerializedName("site_name_long") val site_name_long: String? = null
)

data class LaunchFailureDetails(
	@SerializedName("reason") val reason: String
)


data class Timeline(

	@SerializedName("webcast_liftoff") val webcast_liftoff: Int
)