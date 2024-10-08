package com.yasenenko.nasa.ui.compose

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.yasenenko.nasa.R
import com.yasenenko.nasa.domain.model.Photo


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterialApi::class)
@Composable
fun PhotoList(photos: List<Photo>?, onItemClick: (Photo) -> Unit) {
    if (photos != null) {
        LazyColumn {

            items(photos) { photo ->

                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .defaultMinSize(minHeight = 130.dp)
                        .padding(8.dp),
                    onClick = {
                        onItemClick(photo)
                    }
                ) {
                    ConstraintLayout {

                        val (image, textId, textDate) = createRefs()

                        GlideImage(
                            model = photo.imgSrc,
                            contentDescription = stringResource(R.string.image),
                            modifier = Modifier.constrainAs(image) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }
                        )

                        Text(
                            text = photo.photoId.toString(),
                            color = colorResource(R.color.white),
                            style = TextStyle(
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier
                                .constrainAs(textId) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                }
                                .padding(8.dp)
                        )

                        Text(
                            text = photo.earthDate,
                            color = colorResource(R.color.white),
                            style = TextStyle(
                                fontSize = 16.sp,
                            ),
                            modifier = Modifier
                                .constrainAs(textDate) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
