package com.pjs.soss.ui.loc

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pjs.soss.R
import com.pjs.soss.data.model.loc.DataLoc
import kotlinx.android.synthetic.main.adapter_loc.view.*

class LocAdapter (val context: Context, var dataLoc: ArrayList<DataLoc>):
        RecyclerView.Adapter<LocAdapter.ViewHolder>() {

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val view = view
            fun bing(dataLoc: DataLoc) {
                view.txvNameInc.text = dataLoc.nama_toko
                view.txvLocation.text = dataLoc.alamat
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from( parent.context).inflate( R.layout.adapter_loc, parent, false)
    )



    override fun getItemCount() = dataLoc.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataLoc[position])
        Glide.with(context)
            .load(dataLoc[position].gambar_toko)
            .centerCrop()
            .placeholder(R.drawable.img_no_image)
            .error(R.drawable.img_no_image)
            .into(holder.view.imvImage)
    }

    fun setData(newDataLoc: List<DataLoc>) {
        dataLoc.clear()
        dataLoc.addAll(newDataLoc)
        notifyDataSetChanged()
    }

}