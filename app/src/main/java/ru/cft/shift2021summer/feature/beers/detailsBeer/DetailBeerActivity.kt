package ru.cft.shift2021summer.feature.beers.detailsBeer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.cft.shift2021summer.app.App
import ru.cft.shift2021summer.databinding.ActivityDetailBeerBinding
import ru.cft.shift2021summer.feature.beers.domain.BeerModel

class DetailBeerActivity : AppCompatActivity(), DetailsBeerView {

    private lateinit var binding: ActivityDetailBeerBinding

    companion object {
        private val EXTRA_ID = "EXTRA_ID"
        fun start (context: Context, id: Int) {
            val intent = Intent (context, DetailBeerActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }
            context.startActivity(intent)
        }
    }

    private val presenter by lazy {
        val repository = (application as App).beersRepository
        DetailsBeerPresenter(
            intent.getIntExtra(EXTRA_ID, 0),
            repository!!
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBeerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.attachView(this)
    }

    override fun viewAllInfo(beerModel: BeerModel) {
        binding.tvBeerDescription.text = beerModel.description
        binding.tbBrewersTips.text = beerModel.brewers_tips
    }


}