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

        holder.date.text = currentitem.rideDay
        holder.time.text = currentitem.rideTime
        holder.driveFrom.text = currentitem.driveFrom
        holder.driveTo.text = currentitem.driveTo

    }

    override fun getItemCount(): Int {
        return rideList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val date : TextView = itemView.findViewById(R.id.tvDate)
        val time : TextView = itemView.findViewById(R.id.tvTime)
        val driveFrom : TextView = itemView.findViewById(R.id.tvDriveFrom)
        val driveTo : TextView = itemView.findViewById(R.id.tvDriveTo)

    }
}