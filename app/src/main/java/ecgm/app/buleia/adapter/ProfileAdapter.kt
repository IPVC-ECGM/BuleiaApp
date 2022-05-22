package ecgm.app.buleia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.core.Context
import ecgm.app.buleia.R
import ecgm.app.buleia.activity.ReviewActivity
import ecgm.app.buleia.databinding.ItemListBinding
import ecgm.app.buleia.model.ProfileData

class ProfileAdapter(
    var c: ReviewActivity, var profileList:ArrayList<ProfileData>
    ):RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>()
{
    inner class ProfileViewHolder(var v: ItemListBinding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBinding>(
            inflter, R.layout.item_list,parent,
            false)
        return ProfileViewHolder(v)

    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val newList = profileList[position]
        holder.v.isProfile = profileList[position]
        holder.v.root.setOnClickListener {
            val fromID = newList.fromID
            val toID = newList.toID
            val comt = newList.comt
            val fromIMG = newList.fromIMG

            /**set Data*//*
            val mIntent = Intent(c,NewActivity::class.java)
            mIntent.putExtra("img",img)
            mIntent.putExtra("name",name)
            mIntent.putExtra("info",info)
            c.startActivity(mIntent)*/
        }
    }

    override fun getItemCount(): Int {
        return  profileList.size
    }

}