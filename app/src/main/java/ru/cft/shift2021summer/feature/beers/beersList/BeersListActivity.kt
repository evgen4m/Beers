package ru.cft.shift2021summer.feature.beers.beersList

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.PagerSnapHelper
import ru.cft.shift2021summer.R
import ru.cft.shift2021summer.app.App
import ru.cft.shift2021summer.feature.beers.detailsBeer.DetailBeerActivity
import ru.cft.shift2021summer.databinding.ActivityMainBinding
import ru.cft.shift2021summer.feature.beers.domain.BeerModel
import kotlin.collections.ArrayList


class BeersListActivity : AppCompatActivity(), BeerListView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lim: LinearLayoutManager
    private val presenter by lazy {
        BeerActivityPresenter((application as App).beersRepository)
     }
    private val adapter = BeersAdapter {
        presenter.openDetailsScreen(it)
    }

    val snapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)
        lim = LinearLayoutManager(this, HORIZONTAL,false)
        binding.beersRecycler.layoutManager = lim
        binding.beersRecycler.adapter = adapter
        snapHelper.attachToRecyclerView(binding.beersRecycler)
    }

    override fun onResume() {
        super.onResume()
        presenter.onScreenResumed()
    }

    override fun getBeersList(list: ArrayList<BeerModel>) {
        adapter.beers = list
    }

    override fun openBeerDetailScreen(beerModelId: Int) {
        DetailBeerActivity.start(this, beerModelId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.search_bar)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

        }
        return super.onOptionsItemSelected(item)
    }

}