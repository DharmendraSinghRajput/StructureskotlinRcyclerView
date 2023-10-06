package com.example.structureskotlin.remote

import java.io.File

data class GetAllPostResponse(
    val code: String = "",
    val `data`: List<Data> = listOf(),
    val message: String = "",
    val status: String = ""
) {
    data class Data(
        var User_Like: Int = 0,
        val dtAddedDate: String = "",
        val dtUpdatedDate: Any = Any(),
        val eDelete: String = "",
        val eStatus: String = "",
        val iPostId: Int = 0,
        val iUserId: Int = 0,
        var like_Count: Int = 0,
        val post_lovesit_transactions: List<PostLovesitTransaction> = listOf(),
        val post_transactions: List<PostTransaction> = listOf(),
        val tDescription: String = "",
        val user: User = User(),
        val vEmbedLink: String = "",
        val vEmbedShortLink: String = "",
        val vUniqueCode: String = "",
        val post_share_to: PostShareTo = PostShareTo(),
        val eKids_For: String = "",
        val Comment_Count: Int = 0,
        val follower: Follower = Follower(),
        val vLink: String = "",
        val vLinkDescription: String = "",
        val vLinkImage: String = "",
        val vLocation: String = ""
    ) {
        data class PostLovesitTransaction(
            val dtAddedDate: String = "",
            val dtUpdatedDate: Any = Any(),
            val iPostId: Int = 0,
            val iPostLovesItTransactionId: Int = 0,
            val iUserId: Int = 0
        )

        data class PostTransaction(
            val eCheck: String = "",
            val eImageType: String = "",
            val eType: String = "",
            val iPostId: Int = 0,
            val iPostTransId: Int = 0,
            val vFile: String = "",
            val vHeight: String = "",
            val vWidth: String = "",
            val mediaFile: File = File(""),
        )

        data class User(
            val eUserAccountType: String = "",
            val iUserId: Int = 0,
            val vFirstName: String = "",
            val vImage: String = "",
            val vLastName: String = "",
            val vPersonalSlug: String = ""
        )

        data class PostShareTo(
            val dtAddedDate: String = "",
            val dtUpdatedDate: Any = Any(),
            val eType: String = "",
            val iPostId: Int = 0,
            val iPostShareToId: Int = 0,
            val tHideUsers: String = "",
            val tUsers: String = "",
            val user: User = User()
        )

        data class Follower(
            val RequesterStatus: String = "",
            val eUserAccountType: String = "",
            val iFollowerId: Int = 0,
            val iRequesterId: Int = 0,
            val iUserId: Int = 0
        )
    }
}
