package com.airdroid.helper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airdroid.helper.R
import com.airdroid.helper.data.Device

class DeviceAdapter(
    activity: FragmentActivity,
    private val devices: List<Device>
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = devices.size

    override fun createFragment(position: Int): Fragment {
        return DeviceFragment(devices[position])
    }
}

class DeviceFragment(private val device: Device) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }
}