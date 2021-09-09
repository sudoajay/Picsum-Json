package com.sudoajay.picsum.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.BaseActivity
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.ActivityMainBinding
import com.sudoajay.picsum.helper.InsetDivider
import com.sudoajay.picsum.main.bottomsheet.NavigationDrawerBottomSheet
import com.sudoajay.picsum.main.bottomsheet.SettingBottomSheet
import com.sudoajay.picsum.main.repository.ApiRepository
import com.sudoajay.picsum.main.repository.PersonListAdapter
import com.sudoajay.picsum.main.repository.PersonPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var personPagingAdapter: PersonPagingAdapter
    lateinit var personListAdapter: PersonListAdapter

    var apiRepository: ApiRepository = ApiRepository(this)
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
        viewModel.protoManager.
        dataStoreStatePreferences.data.asLiveData().observe(this) {
            viewModel.isDatabase = it.database
            viewModel.getJsonConverter = it.jsonConverter
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
        apiRepository.getDataFromApi()
        hideProgressAndRefresh()

    }

    private fun setRecyclerView() {
        val divider = getInsertDivider()
        binding.recyclerView.addItemDecoration(divider)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        personListAdapter = PersonListAdapter(this, listOf(), listOf())
        binding.recyclerView.adapter = personListAdapter


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
        val navigationDrawerBottomSheet = NavigationDrawerBottomSheet()
        navigationDrawerBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            navigationDrawerBottomSheet.tag
        )
    }


    fun openSetting() {
        val settingBottomSheet = SettingBottomSheet(this)
        settingBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            settingBottomSheet.tag
        )
    }

    private fun refreshData() {
        Log.e(TAG, "Refresh data call here ")
        apiRepository.getDataFromApi()
        hideProgressAndRefresh()
    }

    private fun hideProgressAndRefresh(){
        if (binding.swipeRefresh.isRefreshing)
            binding.swipeRefresh.isRefreshing = false
        viewModel.hideProgress.value = true
    }


}