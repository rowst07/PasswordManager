package ipca.passwordman.a18064

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PassListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<PassListAdapter.PassViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var passwords = emptyList<Password>() // Cached copy of words

    inner class PassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val PassItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PassViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return PassViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PassViewHolder, position: Int) {
        val current = passwords[position]
        holder.PassItemView.text = current.password
    }

    internal fun setPasswords(passes: List<Password>) {
        this.passwords = passes
        notifyDataSetChanged()
    }

    override fun getItemCount() = passwords.size


}