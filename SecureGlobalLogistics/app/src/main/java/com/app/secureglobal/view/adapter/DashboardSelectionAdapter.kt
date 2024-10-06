import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.secureglobal.R
import com.app.secureglobal.databinding.ItemDashboardSelectionBinding
import com.app.secureglobal.interfaces.OnItemSelected
import com.app.secureglobal.model.dashboard.getDashboardApiResponse.GetMobileDashboardDetailDto
import com.app.secureglobal.view.adapter.DashboardMenuViewHolder

import com.app.secureglobal.viewmodel.DashboardMenuViewModel

class DashboardSelectionAdapter(val context: Context, private val list: ArrayList<GetMobileDashboardDetailDto>, val viewModel: DashboardMenuViewModel, val onItemSelected: OnItemSelected<GetMobileDashboardDetailDto>,) :  RecyclerView.Adapter<DashboardMenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardMenuViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val binder = DataBindingUtil.inflate<ItemDashboardSelectionBinding>(
            layoutInflater,
            R.layout.item_dashboard_selection,
            parent,
            false
        )

        return DashboardMenuViewHolder(context,binder, viewModel)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DashboardMenuViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(list[position])
        if (!list[position].getAdditionalCaption().isNullOrEmpty()){
            holder.binding.llCounter.visibility = View.VISIBLE
        }
        else{
            holder.binding.llCounter.visibility = View.GONE
        }

        holder.binding.txtCounter.text = list[position].getAdditionalCaption()!!.trim()

        holder.binding.llMain.setOnClickListener {
            onItemSelected.onItemSelected(list[position], position)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }
}