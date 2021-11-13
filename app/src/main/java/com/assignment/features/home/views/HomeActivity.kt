package com.assignment.features.home.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.assignment.MyApp
import com.assignment.features.R
import com.assignment.features.databinding.ActivityHomeBinding
import com.assignment.features.home.viewModel.HomeActivityViewModel
import com.assignment.network.utils.NetworkStatus
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject




class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var networkDataActivityViewModel: HomeActivityViewModel

    @Inject
    lateinit var networkDataAdapter: HomeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of home component by grabbing the factory from the app graph
        val animeComponent = (application as MyApp).appComponent
            .homeComponent().create()
        // Injects this activity to the just created home component
        animeComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_home)

        prepareView()
        callApiAndUpdateUI()
    }

    private fun prepareView() {
        binding.recycleViewNetworkData.itemAnimator = DefaultItemAnimator()
        binding.recycleViewNetworkData.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recycleViewNetworkData.adapter = networkDataAdapter
    }

    private fun callApiAndUpdateUI() {
        binding.networkDataProgressBar.visibility = View.VISIBLE
        networkDataActivityViewModel.networkLiveData.observe(this,
            { baseDataStore ->
                binding.networkDataProgressBar.visibility = View.GONE

                if (baseDataStore != null) {
                    networkDataAdapter.addItemList(baseDataStore.userlist!!)
                } else {
                    networkDataActivityViewModel.statusLiveData.value = NetworkStatus.FAIL
                }
            })


        // status of actions
        networkDataActivityViewModel.statusLiveData.observe(this,
            { status ->
                binding.networkDataProgressBar.visibility = View.GONE
                onNetWorkStateChanged(status)
            })
        networkDataActivityViewModel.fetchDataDetail()
    }


    private fun onNetWorkStateChanged(state: NetworkStatus){
        when (state) {
            NetworkStatus.INTERNET_CONNECTION -> showSnackBar(getString(R.string.msg_no_internet_network))
            NetworkStatus.SERVER_ERROR -> showSnackBar(getString(R.string.msg_server_error))
            NetworkStatus.FAIL -> showSnackBar(getString(R.string.msg_something_went_wrong))
            NetworkStatus.NO_RECORDS -> showSnackBar(getString(R.string.msg_no_records))
        }
    }
    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)
            .show()
    }
}