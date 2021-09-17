package com.example.cleanarch.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.request.RequestOptions
import com.example.cleanarch.R
import com.example.cleanarch.compose.makeText
import com.example.cleanarch.model.NewsData
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private val viewModel: NewsFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        initView()
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val loading: Boolean by viewModel.loading.observeAsState(initial = true)
                LoadingScreen(isLoading = loading) {
                    NewsList(viewModel = viewModel)
                }
            }
        }
    }

    private fun initView(){
        viewModel.errorMessage().observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    @Composable
    fun NewsList(viewModel: NewsFragmentViewModel){
        val data: ArrayList<NewsData> by viewModel.data.observeAsState(arrayListOf())
        LazyColumn(modifier = Modifier.padding(top = 6.dp)){
            items(data){ data ->
                NewsItem(data = data, listener = {
                    findNavController().navigate(R.id.action_newsFragment_to_detailFragment, Bundle().apply { putParcelable("data", it) })
                })
            }
        }
    }
}

@Composable
fun NewsItem(data: NewsData?, listener: (NewsData?)->Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)
        .clickable(onClick = { listener(data) })
    ) {
        Card(elevation = 6.dp, shape = RoundedCornerShape(6.dp), modifier = Modifier.fillMaxWidth()) {
            Row {
                GlideImage(
                    imageModel = data?.urlToImage!!, modifier = Modifier
                        .width(75.dp)
                        .height(75.dp),
                    requestOptions = RequestOptions().override(75,75).centerCrop(),
                    error = painterResource(id = R.drawable.ic_launcher_background)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column (modifier = Modifier.padding(6.dp)) {
                    makeText(text = data.title, fontSize = 16, fontWeight = FontWeight.Bold, paddingTop = 0, maxLines = 2)
                    makeText(text = data.publishedAt, fontSize = 14, paddingTop = 0)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(
    isLoading: Boolean,
    content: @Composable () -> Unit
) = if (isLoading
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
} else {
    content()
}