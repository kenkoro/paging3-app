package com.kenkoro.paging3.feature_pagination.presentation.beer_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kenkoro.paging3.feature_pagination.domain.model.Beer
import com.kenkoro.paging3.ui.theme.Paging3Theme

@Composable
fun BeerItem(
  beer: Beer,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    elevation = CardDefaults.cardElevation(
      defaultElevation = 4.dp
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(IntrinsicSize.Max),
    ) {
      AsyncImage(
        model = beer.imageUrl,
        contentDescription = beer.name,
        modifier = Modifier
          .weight(1F)
          .height(150.dp)
      )
      Spacer(modifier = Modifier.width(16.dp))
      Column(
        modifier = Modifier
          .weight(3F)
          .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
      ) {
        Column(
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = beer.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = beer.tagline,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.fillMaxWidth(),
            fontStyle = FontStyle.Italic
          )
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = beer.description,
            modifier = Modifier.fillMaxWidth(),
          )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
          verticalArrangement = Arrangement.Bottom,
          horizontalAlignment = Alignment.End
        ) {
          Text(
            text = "First brewed in ${beer.firstBrewed}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.labelSmall
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun BeerPreview() {
  Paging3Theme {
    BeerItem(
      beer = Beer(
        id = 1,
        name = "Beer",
        tagline = "Cool beer",
        firstBrewed = "10/2023",
        description = "This is a description of this beer.",
        imageUrl = null
      ),
      modifier = Modifier.fillMaxWidth()
    )
  }
}