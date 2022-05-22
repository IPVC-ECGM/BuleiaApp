package ecgm.app.buleia.activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ecgm.app.buleia.R

class MyAdapter(private val rideList : ArrayList<Ride>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ride_item, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = rideList[position]

        holder.date.text = currentitem.date
        holder.drop.text = currentitem.drop
        holder.name.text = currentitem.name
        holder.pick1.text = currentitem.pick
        holder.pick2.text = currentitem.pickA
        holder.pick3.text = currentitem.pickB

    }

    override fun getItemCount(): Int {

        return rideList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        val date : TextView = itemView.findViewById(R.id.tvData)
        val drop : TextView = itemView.findViewById(R.id.tvDrop)
        val name : TextView = itemView.findViewById(R.id.tvNome)
        val pick1 : TextView = itemView.findViewById(R.id.tvPick1)
        val pick2 : TextView = itemView.findViewById(R.id.tvPick2)
        val pick3 : TextView = itemView.findViewById(R.id.tvPick3)

    }
}