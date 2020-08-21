package net.resulbal.bitcointicker.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_profile.logout
import kotlinx.android.synthetic.main.fragment_profile.nameText
import net.resulbal.bitcointicker.R
import net.resulbal.bitcointicker.data.source.ApiResult
import net.resulbal.bitcointicker.di.ViewModelFactory
import net.resulbal.bitcointicker.extensions.startActivityClearStack
import net.resulbal.bitcointicker.ui.base.BaseFragment
import net.resulbal.bitcointicker.ui.login.LoginActivity
import timber.log.Timber
import javax.inject.Inject

class ProfileFragment: BaseFragment(), ProfileView.View {

  private lateinit var viewModel: ProfileViewModel

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_profile, container, false)

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    viewModel = ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    viewModel.user?.observe(requireActivity(), Observer { result ->
      when (result) {
        is ApiResult.Loading -> Timber.e("Loading")
        is ApiResult.Failure -> Toast.makeText(
          requireContext(), "Something wrong...", Toast.LENGTH_SHORT
        ).show()
        is ApiResult.Success -> {
          nameText.text = result.data.name
        }
      }
    })

    logout.setOnClickListener {
      viewModel.logout(requireContext()) {
        requireContext().startActivityClearStack<LoginActivity>()
      }
    }
  }
}