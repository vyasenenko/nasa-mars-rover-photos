package com.yasenenko.nasa.ui.compose

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.yasenenko.nasa.R
import com.yasenenko.nasa.ui.details.PhotoDetailsFragment
import com.yasenenko.nasa.ui.theme.Theme

@Composable
fun ComposeScene(composeViewModel: ComposeViewModel, onNavigate: (Int, Bundle?) -> Unit) {
    Theme {

        val isLoading by composeViewModel.progressEvent.observeAsState(false)
        val hasConnection by composeViewModel.networkConnection.observeAsState(true)
        val items by composeViewModel.photos.observeAsState()

        LaunchedEffect(key1 = hasConnection) {
            composeViewModel.fetchPhotos()
        }

        Column {
            TopAppBar(
                title = { Text(stringResource(R.string.title_compose)) },
                backgroundColor = colorResource(R.color.colorPrimary),
                actions = {
                    IconButton(onClick = {
                        composeViewModel.changeSort()
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Sort")
                    }
                }
            )

            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            if (!hasConnection && items.isNullOrEmpty()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.height(60.dp))

                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_wifi_off_24dp),
                        contentDescription = "No Internet",
                        Modifier
                            .background(
                                color = colorResource(R.color.colorPrimaryTransparent),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .width(60.dp)
                            .height(60.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(
                        text = stringResource(R.string.no_internet),
                        modifier = Modifier.padding(top = 30.dp)
                    )
                }
            }

            PhotoList(photos = items) {
                onNavigate(R.id.action_compose_to_details, Bundle().apply {
                    putParcelable(PhotoDetailsFragment.EXTRA_PHOTO_KEY, it)
                })
            }
        }
    }
}