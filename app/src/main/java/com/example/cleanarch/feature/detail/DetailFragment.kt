package com.example.cleanarch.feature.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cleanarch.R
import com.example.cleanarch.compose.makeText
import com.example.cleanarch.model.NewsData
import com.skydoves.landscapist.glide.GlideImage

class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val args: NewsData? = arguments?.getParcelable("data")
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                detailNews(args)
            }
        }
    }
}

@Composable
fun detailNews(args: NewsData?) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        GlideImage(
            imageModel = args?.urlToImage!!,
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)) {
            makeText(text = args.title, fontSize = 16, fontWeight = FontWeight.Bold)
            makeText(text = args.author, fontSize = 14)
            makeText(text = args.description, fontSize = 12)
        }
    }
}