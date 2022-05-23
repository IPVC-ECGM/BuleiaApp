package ecgm.app.buleia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ecgm.app.buleia.R
import ecgm.app.buleia.model.Ride

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
        holder.destinationTo.text = currentitem.driveTo
        holder.pick1.text = currentitem.pick1
        holder.pick2.text = currentitem.pick1
        holder.pick3.text = currentitem.pick1

        val expandableLayout : Boolean = rideList[position].expandableLayout
        holder.expandableLayout.visibility = if (expandableLayout) View.VISIBLE else View.GONE

        holder.linearLayout.setOnClickListener {
            val myList = rideList[position]
            myList.expandableLayout = !myList.expandableLayout
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return rideList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val date : TextView = itemView.findViewById(R.id.dateTo)
        val time : TextView = itemView.findViewById(R.id.timeTo)
        val driveFrom : TextView = itemView.findViewById(R.id.startTo)
        val driveTo : TextView = itemView.findViewById(R.id.endTo)
        val destinationTo : TextView = itemView.findViewById(R.id.destinatioTo)
        val pick1 : TextView = itemView.findViewById(R.id.vizigolo1)
        val pick2 : TextView = itemView.findViewById(R.id.vizigolo2)
        val pick3 : TextView = itemView.findViewById(R.id.vizigolo3)

        var linearLayout : LinearLayout = itemView.findViewById(R.id.rowLineas)
        var expandableLayout : RelativeLayout = itemView.findViewById(R.id.expandableTo)
    }
}