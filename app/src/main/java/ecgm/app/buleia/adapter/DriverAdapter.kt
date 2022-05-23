package ecgm.app.buleia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import ecgm.app.buleia.R
import ecgm.app.buleia.model.DriverModel
import kotlinx.android.synthetic.main.activity_perfil.*
import org.w3c.dom.Text


class DriverAdapter(private val driverList : ArrayList<DriverModel>) : RecyclerView.Adapter<DriverAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.driver_item, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = driverList[position]

        holder.driverWhere?.text = currentitem.driverWhere
        holder.timeDriver?.text = currentitem.timeDriver
        holder.pickUpLocation?.text = currentitem.pickUpLocation
        holder.numberSeats?.text = currentitem.numberSeats
        holder.driverName?.text = currentitem.driverName
        holder.numberBuleia?.text = currentitem.numberBuleia
        holder.desProfile?.text = currentitem.desProfile
        holder.matriculaCarro?.text = currentitem.matriculaCarro
        holder.reviewStar?.text = currentitem.reviewStar

        val expandableLayout2 : Boolean = driverList[position].expandableLayout2
        holder.expandableLayout2?.visibility = if (expandableLayout2) View.VISIBLE else View.GONE

        holder.linearLayout?.setOnClickListener {
            val myList = driverList[position]
            myList.expandableLayout2 = !myList.expandableLayout2
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return driverList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var driverWhere : TextView ?= itemView.findViewById(R.id.destinatioTo221)
        var timeDriver : TextView ?= itemView.findViewById(R.id.timeTo2)
        var pickUpLocation : TextView ?= itemView.findViewById(R.id.startTo2)
        var numberSeats : TextView ?= itemView.findViewById(R.id.seatsTo2)
        val driverName : TextView ?= itemView.findViewById(R.id.nameTo2)
        val numberBuleia: TextView ?= itemView.findViewById(R.id.clients)
        var reviewStar: TextView ?= itemView.findViewById(R.id.clients2)
        var desProfile: TextView ?= itemView.findViewById(R.id.aboutTxet)
        var matriculaCarro: TextView ?= itemView.findViewById(R.id.matricula)
        var linearLayout : LinearLayout? = itemView.findViewById(R.id.dropdown22)
        var expandableLayout2 : RelativeLayout ?= itemView.findViewById(R.id.expandableTo2)
    }
}