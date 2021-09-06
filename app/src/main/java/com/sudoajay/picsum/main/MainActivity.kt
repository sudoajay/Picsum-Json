package com.sudoajay.picsum.main

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sudoajay.picsum.BaseActivity
import com.sudoajay.picsum.R
import com.sudoajay.picsum.databinding.ActivityMainBinding
import com.sudoajay.picsum.helper.InsetDivider
import com.sudoajay.picsum.navigation.NavigationDrawerBottomSheet
import com.sudoajay.picsum.setting.SettingBottomSheet
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
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


//        val apiInterface = PicsumInterfaceBuilder.getApiInterface()
//        val call = apiInterface?.getPerson()
//
//        call?.enqueue(object : Callback<List<Person>> {
//
//            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
////                Log.e("$TAG+Response", Gson().toJson(response))
//                Log.e("$TAG+Response", response.message())
//                Log.e("$TAG+Response"," Start ")
//                response.body()?.forEach {
//                    Log.e("$TAG + Response", it.toString())
//                }
//                Log.e("$TAG+Response","End")
//
//            }
//            override fun onFailure(call: Call<List<Person>>, t: Throwable)
//            {
//                Log.e("$TAG +onFailure",t.printStackTrace().toString() + " -- ${t.toString()}")
//            }
//        })

    }

    override fun onResume() {
        setReference()
        super.onResume()
    }

    private fun setReference() {

        //      Setup Swipe Refresh
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(applicationContext, R.color.swipeBgColor)
        )

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
    }

    private fun setRecyclerView() {

        val recyclerView = binding.recyclerView
        val divider = getInsertDivider()
        recyclerView.addItemDecoration(divider)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

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
            R.id.refresh_optionMenu->refreshActivity()

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
        val darkModeBottomSheet = SettingBottomSheet()
        darkModeBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            darkModeBottomSheet.tag
        )
    }

    private fun refreshActivity(){

    }


}