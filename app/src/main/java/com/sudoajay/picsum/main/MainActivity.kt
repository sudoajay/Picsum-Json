package com.sudoajay.picsum.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.BaseActivity
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.ActivityMainBinding
import com.sudoajay.picsum.helper.InsetDivider
import com.sudoajay.picsum.main.bottomsheet.NavigationDrawerBottomSheet
import com.sudoajay.picsum.main.bottomsheet.SettingBottomSheet
import com.sudoajay.picsum.main.repository.ApiRepository
import com.sudoajay.picsum.main.repository.noDatabase.PersonListAdapter
import com.sudoajay.picsum.main.repository.pagingSource.PersonPagingAdapterGson
import com.sudoajay.picsum.main.repository.pagingSource.PersonPagingAdapterJackson
import com.sudoajay.picsum.main.repository.pagingSource.PersonPagingAdapterMoshi
import com.sudoajay.picsum.main.repository.remoteMediator.PersonLocalPagingAdapterGson
import com.sudoajay.picsum.main.repository.remoteMediator.PersonLocalPagingAdapterJackson
import com.sudoajay.picsum.main.repository.remoteMediator.PersonLocalPagingAdapterMoshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import androidx.fragment.app.activityViewModels

@AndroidEntryPoint
class MainActivity :  BaseActivity() {

    val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var personListAdapter: PersonListAdapter
    @Inject
    lateinit var  personPagingAdapterJackson: PersonPagingAdapterJackson
    @Inject
     lateinit var personPagingAdapterGson: PersonPagingAdapterGson
    @Inject
     lateinit var personPagingAdapterMoshi: PersonPagingAdapterMoshi
    @Inject
     lateinit var personLocalPagingAdapterGson: PersonLocalPagingAdapterGson
    @Inject
     lateinit var personLocalPagingAdapterJackson: PersonLocalPagingAdapterJackson
    @Inject
    lateinit var personLocalPagingAdapterMoshi: PersonLocalPagingAdapterMoshi
    @Inject
    lateinit var navigationDrawerBottomSheet : NavigationDrawerBottomSheet
    @Inject
    lateinit var settingBottomSheet:SettingBottomSheet


    private var apiRepository: ApiRepository = ApiRepository(this)
    private var searchData = ""
    private var isDarkTheme: Boolean = false
    private var TAG = "MainActivityTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDarkTheme = isSystemDefaultOn()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isDarkTheme) {
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                    true
            }

        }


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

    }



    override fun onResume() {
        setReference()
        super.onResume()
        protoManager.
        dataStoreStatePreferences.data.asLiveData().observe(this) {
            getDatabase = it.database
            getJsonConverter = it.jsonConverter
            refreshData()
        }
    }

    private fun setReference() {

        //      Setup Swipe Refresh
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(applicationContext, R.color.swipeBgColor)
        )

        binding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }


        //         Setup BottomAppBar Navigation Setup
        binding.bottomAppBar.navigationIcon?.mutate()?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.setTint(
                    ContextCompat.getColor(applicationContext, R.color.colorAccent)
                )
            }
            binding.bottomAppBar.navigationIcon = it
        }

        setSupportActionBar(binding.bottomAppBar)

        setRecyclerView()

        showProgressAndHideRefresh()

    }

    private fun setRecyclerView() {
        val divider = getInsertDivider()
        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        protoDataChange()

    }

    private fun protoDataChange(){

        when (getDatabase) {
            getString(R.string.remote_mediator_text) -> {
                when (getJsonConverter) {
                    getString(R.string.jacksonJson_text) -> {
                        binding.recyclerView.adapter = personLocalPagingAdapterJackson
                        lifecycleScope.launch {

                            apiRepository.getRemoteMediatorSourceWithNetworkJackson()
                                .collectLatest { pagingData ->
                                    personLocalPagingAdapterJackson.submitData(pagingData)
                                  setValueHideProgress(true)
                                }
                            setValueHideProgress(personPagingAdapterJackson.itemCount !=0 )
                        }
                    }
                    getString(R.string.gsonJson_text) -> {
                        binding.recyclerView.adapter = personLocalPagingAdapterGson
                        Log.e(TAG, "bind:  I m here Gson - ")

                        lifecycleScope.launch {
                            Log.e(TAG, "bind:  I m here Gson -- ")

                            apiRepository.getRemoteMediatorSourceWithNetworkGson()
                                .collectLatest { pagingData ->
                                    Log.e(TAG, "bind:  I m here Gson --- ")
                                    personLocalPagingAdapterGson.submitData(pagingData)
                                    setValueHideProgress(true)
                                }
                            setValueHideProgress(personLocalPagingAdapterGson.itemCount !=0 )

                        }
                    }
                    else -> {
                        binding.recyclerView.adapter = personLocalPagingAdapterMoshi
                        Log.e(TAG, "bind:  I m here Gson - ")

                        lifecycleScope.launch {
                            Log.e(TAG, "bind:  I m here Gson -- ")

                            apiRepository.getRemoteMediatorSourceWithNetworkMoshi()
                                .collectLatest { pagingData ->
                                    Log.e(TAG, "bind:  I m here Gson --- ")
                                    personLocalPagingAdapterMoshi.submitData(pagingData)
                                    setValueHideProgress(true)
                                }
                            setValueHideProgress(personLocalPagingAdapterMoshi.itemCount !=0 )

                        }
                    }
                }
            }
            getString(R.string.paging_source_text) -> {

                when (getJsonConverter) {
                    getString(R.string.jacksonJson_text) -> {
                        binding.recyclerView.adapter = personPagingAdapterJackson
                        lifecycleScope.launch {
                            apiRepository.getPagingJacksonSourceWithNetwork()
                                .map { pagingData->pagingData.filter { if (viewModel.searchValue != "") it.name.lowercase().contains(viewModel.searchValue)  else true  } }
                                .collectLatest { pagingData ->
                                    personPagingAdapterJackson.submitData(pagingData)
                                    setValueHideProgress(true)

                                }
                            setValueHideProgress(personPagingAdapterJackson.itemCount !=0 )

                        }

                    }
                    getString(R.string.gsonJson_text) -> {
                        binding.recyclerView.adapter = personPagingAdapterGson

                        lifecycleScope.launch {
                            apiRepository.getPagingGsonSourceWithNetwork()
                                .map { pagingData->pagingData.filter {  if (viewModel.searchValue != "") it.name.lowercase().contains(viewModel.searchValue)  else true  } }
                                .collectLatest { pagingData ->
                                    personPagingAdapterGson.submitData(pagingData)
                                    setValueHideProgress(true)
                                }
                            setValueHideProgress(personPagingAdapterGson.itemCount !=0 )

                        }
                    }
                    else -> {
                        binding.recyclerView.adapter = personPagingAdapterMoshi

                        lifecycleScope.launch {
                            apiRepository.getPagingMoshiSourceWithNetwork()
                                .map { pagingData->pagingData.filter {  if (viewModel.searchValue != "") it.name.lowercase().contains(viewModel.searchValue)  else true   } }
                                .collectLatest { pagingData ->
                                    Log.e(TAG, "Paging source:  I m here Gson --- ")
                                    personPagingAdapterMoshi.submitData(pagingData)
                                    setValueHideProgress(true)
                                }
                            setValueHideProgress(personPagingAdapterMoshi.itemCount !=0 )

                        }
                    }
                }

            }
            else -> { // Note the block
                binding.recyclerView.adapter = personListAdapter
                apiRepository.getDataFromApi()

            }
        }


    }

   private fun setValueHideProgress(boolean: Boolean){
         viewModel.hideProgress.postValue(boolean)
    }

    private fun getInsertDivider(): RecyclerView.ItemDecoration {
        val dividerHeight = resources.getDimensionPixelSize(R.dimen.divider_height)
        val dividerColor = ContextCompat.getColor(
            applicationContext,
            R.color.divider
        )
        val marginLeft = resources.getDimensionPixelSize(R.dimen.divider_inset)
        return InsetDivider.Builder(this)
            .orientation(InsetDivider.VERTICAL_LIST)
            .dividerHeight(dividerHeight)
            .color(dividerColor)
            .insets(marginLeft, 0)
            .build()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_toolbar_menu, menu)
        val actionSearch = menu.findItem(R.id.search_optionMenu)
        manageSearch(actionSearch)

        return super.onCreateOptionsMenu(menu)
    }

    private fun manageSearch(searchItem: MenuItem) {
        val searchView =
            searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        manageFabOnSearchItemStatus(searchItem)
        manageInputTextInSearchView(searchView)
    }

    private fun manageFabOnSearchItemStatus(searchItem: MenuItem) {
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                binding.deleteFloatingActionButton.hide()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                binding.deleteFloatingActionButton.show()
                return true
            }
        })
    }
    private fun manageInputTextInSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val query: String = newText.lowercase(Locale.ROOT).trim { it <= ' ' }
                viewModel.searchValue = query
                refreshData()
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> showNavigationDrawer()
            R.id.setting_optionMenu -> openSetting()
            R.id.refresh_optionMenu -> refreshData()

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun showNavigationDrawer() {
        navigationDrawerBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            navigationDrawerBottomSheet.tag
        )
    }


    fun openSetting() {
        settingBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            settingBottomSheet.tag
        )

    }

    private fun refreshData() {
        Log.e(TAG, "Refresh data call here ")
       protoDataChange()
        showProgressAndHideRefresh()
    }

    private fun showProgressAndHideRefresh(){
        if (binding.swipeRefresh.isRefreshing)
            binding.swipeRefresh.isRefreshing = false
        viewModel.hideProgress.value = false
    }


}