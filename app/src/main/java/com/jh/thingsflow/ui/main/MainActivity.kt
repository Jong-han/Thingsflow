package com.jh.thingsflow.ui.main

import android.util.Log
import androidx.activity.viewModels
import com.jh.thingsflow.BR
import com.jh.thingsflow.R
import com.jh.thingsflow.base.BaseActivity
import com.jh.thingsflow.databinding.ActivityMainBinding
import com.jh.thingsflow.ext.repeatOnStarted
import com.jh.thingsflow.ui.dialog.InputDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val bindingVariable: Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    private val issueAdapter by lazy { IssueAdapter() }

    override fun initViewAndEvent() {

        dataBinding.rvIssue.adapter = issueAdapter

        repeatOnStarted {
            viewModel.org.collect {
                viewModel.searchRepo(it, viewModel.repo.value)
            }
        }

        repeatOnStarted {
            viewModel.repo.collect {
                viewModel.searchRepo(viewModel.org.value, it)
            }
        }

        repeatOnStarted {
            viewModel.viewEvent.collect {
                when (it) {
                    MainViewModel.ViewEvent.ClickTitle -> {
                        val dialog = InputDialog { org: String, repo: String ->
                            viewModel.updateOrgAndRepo(org, repo)
                        }
                        dialog.show(supportFragmentManager, "TAG")
                    }
                }
            }
        }

//        repeatOnStarted {
//            viewModel.getIssueList().collect { resource ->
//                when (resource) {
//                    is Resource.Loading -> {
//                        Toast.makeText(this@MainActivity, "로딩중", Toast.LENGTH_SHORT).show()
//                    }
//                    is Resource.Success -> {
//                        Toast.makeText(this@MainActivity, "성공", Toast.LENGTH_SHORT).show()
//                        val data = resource.data
//                        data?.let { viewModel.insertRepo(it) }
//                    }
//                    is Resource.Error -> {
//                        Toast.makeText(this@MainActivity, "실패:: ${resource.errorMsg}", Toast.LENGTH_SHORT).show()
//                        Log.i("asdf","${resource.errorMsg}")
//                    }
//                }
//            }
//        }

        repeatOnStarted {
            viewModel.getIssueListFromLocal().collect {
                Log.i("asdf","${it.size}")
                issueAdapter.submitList(it)

                Log.i("asdfasdf","${issueAdapter.currentList.size}")
            }
        }

    }
}