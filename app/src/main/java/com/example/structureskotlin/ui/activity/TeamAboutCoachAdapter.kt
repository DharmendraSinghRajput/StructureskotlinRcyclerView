package com.example.structureskotlin.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.structureskotlin.R
import com.example.structureskotlin.databinding.RowItemsBinding
import com.example.structureskotlin.remote.GetAllPostResponse

class TeamAboutCoachAdapter(val onClick: (position: Int, viewId: Int) -> Unit) : RecyclerView.Adapter<TeamAboutCoachAdapter.TeamAboutCoachViewHolder>() {

    private var getAllTeamMemberDataResponse = listOf<GetAllPostResponse.Data>()

    inner class TeamAboutCoachViewHolder(var mBinding: RowItemsBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAboutCoachViewHolder = TeamAboutCoachViewHolder(RowItemsBinding.inflate(
        LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TeamAboutCoachViewHolder, position: Int) {
        holder.mBinding.apply {
            val followData = getAllTeamMemberDataResponse[position]

            getAllTeamMemberDataResponse[position].apply {
                tvName.text="${user.vFirstName ?: ""} ${user.vLastName ?: ""}"
                GeneralFunctions.loadImage(root.context, followData.user.vImage,ivPost)
                root.setOnClickListener { onClick(position, -1) }
             /*   imgEdit.setOnClickListener {
                    onClick(position, R.id.imgEdit)

                }*/
            }


        }
    }

    override fun getItemCount(): Int = getAllTeamMemberDataResponse.size

    fun setData(chatTeamList: List<GetAllPostResponse.Data>) {
        this.getAllTeamMemberDataResponse = chatTeamList
        notifyDataSetChanged()
    }
}