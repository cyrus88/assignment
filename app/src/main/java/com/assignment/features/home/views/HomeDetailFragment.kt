package com.assignment.features.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.assignment.features.R
import com.assignment.features.databinding.FragmentHomeDetailBinding
import com.assignment.network.model.Users
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HomeDetailFragment(private var user: Users) :
    BottomSheetDialogFragment() {

    private lateinit var mBinding: FragmentHomeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_detail, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // update value
        mBinding.tvDialogeDataDetailYear.text = getDataDetail()
        mBinding.ivDialogeClose.setOnClickListener{
            dismiss()
        }

    }


    private fun getDataDetail(): String {

        return " Anime Id -> " + user.id + " \n\n" +
                " Anime Name-> " + user.name + " \n\n"
    }
}