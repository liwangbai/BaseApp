package com.wpy.core.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wpy.utils.ext.clickExt

/**
 * Created by Wangbai.
 * Date: 2023-07-18 23:49:00
 * Desc:
 */
abstract class BaseAdapter<T, B : ViewDataBinding>(internal var items: MutableList<T>, private var layoutRes: Int) : RecyclerView.Adapter<BaseAdapter<T, B>.ViewHolder>() {
    private var itemClickListener: ((item: T, binding: B) -> Unit)? = null
    private var isLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(inflater, layoutRes, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setNewData(newItems: List<T>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val mBinding: B) : RecyclerView.ViewHolder(mBinding.root) {

        fun bind(item: T) {
            bindItem(mBinding, item)
            mBinding.executePendingBindings()
            mBinding.root.clickExt {
                itemClickListener?.invoke(item, mBinding)
            }
            bindAfterExecute(mBinding, item)
        }

    }

    abstract fun bindItem(binding: B, item: T)

    open fun bindAfterExecute(binding: B, item: T) {}

    fun setItemClick(click: (item: T, binding: B) -> Unit) {
        itemClickListener = click
    }

    fun setLoadMoreListener(recyclerView: RecyclerView, loader: () -> Unit) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(ry: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(ry, dx, dy)
                val layoutManager = ry.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItem >= totalItemCount - 10 && dy > 0) {
                    if (!isLoading) {
                        loader()
                        isLoading = true
                    }
                }
            }

            override fun onScrollStateChanged(ry: RecyclerView, newState: Int) {
                super.onScrollStateChanged(ry, newState)
                val layoutManager = ry.layoutManager as LinearLayoutManager
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItem >= totalItemCount - 10 && newState == 0) {
                    if (!isLoading) {
                        loader()
                        isLoading = true
                    }
                }
            }
        })
    }

    fun setLoadComplete() {
        isLoading = false
    }

    fun setLoadFail() {
        isLoading = false
    }

    fun addData(data: List<T>) {
        items.addAll(data)
        notifyItemRangeInserted(items.size - data.size, data.size)
    }
}