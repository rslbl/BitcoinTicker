package net.resulbal.bitcointicker.ui.coinDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.di.ViewModelFactory
import net.resulbal.bitcointicker.ui.base.BaseFragment
import javax.inject.Inject

class CoinDetailFragment: BaseFragment(), CoinDetailView.View {

  private lateinit var viewModel: CoinDetailViewModel

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  private val args by navArgs<CoinDetailFragmentArgs>()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_coin_detail, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProvider(this, viewModelFactory).get(CoinDetailViewModel::class.java)

    if (args.state.id != null) {
      viewModel.setData(args.state)
    } else {
      findNavController().popBackStack()
    }
  }

}