package com.wpy.ui

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.wpy.utils.ext.clickOpt

class BaseTabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private lateinit var homeBtn: LinearLayout
    private lateinit var friendBtn: LinearLayout
    private lateinit var moreBtn: LinearLayout
    private lateinit var meBtn: LinearLayout

    private lateinit var homeIv: ImageView
    private lateinit var friendIv: ImageView
    private lateinit var moreIv: ImageView
    private lateinit var meIv: ImageView

    private lateinit var homeTv: TextView
    private lateinit var friendTv: TextView
    private lateinit var moreTv: TextView
    private lateinit var meTv: TextView

    private lateinit var listener: TabBtnClickCallback
    init {
        LayoutInflater.from(context).inflate(R.layout.tab_container, this)
        bindView()
    }

    private fun bindView(){
        homeBtn = findViewById(R.id.homeWrap)
        friendBtn = findViewById(R.id.friendWrap)
        moreBtn = findViewById(R.id.moreWrap)
        meBtn = findViewById(R.id.meWrap)

        homeIv = findViewById(R.id.homeIv)
        friendIv = findViewById(R.id.friendIv)
        moreIv = findViewById(R.id.moreIv)
        meIv = findViewById(R.id.meIv)

        homeTv = findViewById(R.id.homeTv)
        friendTv = findViewById(R.id.friendTv)
        moreTv = findViewById(R.id.moreTv)
        meTv = findViewById(R.id.meTv)
    }

    fun setClickListener(callback: TabBtnClickCallback) {
        listener = callback
        homeBtn.clickOpt {
            homeIv.setImageResource(R.mipmap.weixin_pressed)
            friendIv.setImageResource(R.mipmap.contact_list_normal)
            moreIv.setImageResource(R.mipmap.find_normal)
            meIv.setImageResource(R.mipmap.profile_normal)

            homeTv.setTextColor(Color.parseColor("#FF07C160"))
            friendTv.setTextColor(Color.parseColor("#222222"))
            moreTv.setTextColor(Color.parseColor("#222222"))
            meTv.setTextColor(Color.parseColor("#222222"))

            listener.invoke(0)
        }
        friendBtn.clickOpt {
            listener.invoke(1)
            homeIv.setImageResource(R.mipmap.weixin_normal)
            friendIv.setImageResource(R.mipmap.contact_list_pressed)
            moreIv.setImageResource(R.mipmap.find_normal)
            meIv.setImageResource(R.mipmap.profile_normal)

            homeTv.setTextColor(Color.parseColor("#222222"))
            friendTv.setTextColor(Color.parseColor("#FF07C160"))
            moreTv.setTextColor(Color.parseColor("#222222"))
            meTv.setTextColor(Color.parseColor("#222222"))

        }
        moreBtn.clickOpt {
            listener.invoke(2)
            homeIv.setImageResource(R.mipmap.weixin_normal)
            friendIv.setImageResource(R.mipmap.contact_list_normal)
            moreIv.setImageResource(R.mipmap.find_pressed)
            meIv.setImageResource(R.mipmap.profile_normal)

            homeTv.setTextColor(Color.parseColor("#222222"))
            friendTv.setTextColor(Color.parseColor("#222222"))
            moreTv.setTextColor(Color.parseColor("#FF07C160"))
            meTv.setTextColor(Color.parseColor("#222222"))

        }
        meBtn.clickOpt {
            listener.invoke(3)
            homeIv.setImageResource(R.mipmap.weixin_normal)
            friendIv.setImageResource(R.mipmap.contact_list_normal)
            moreIv.setImageResource(R.mipmap.find_normal)
            meIv.setImageResource(R.mipmap.profile_pressed)

            homeTv.setTextColor(Color.parseColor("#222222"))
            friendTv.setTextColor(Color.parseColor("#222222"))
            moreTv.setTextColor(Color.parseColor("#222222"))
            meTv.setTextColor(Color.parseColor("#FF07C160"))
        }
    }

}
interface TabBtnClickCallback{
    fun invoke(position: Int)
}
