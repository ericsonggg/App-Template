package com.ejsong.apptemplate.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ejsong.apptemplate.R
import com.ejsong.apptemplate.base.BaseActivity
import com.ejsong.apptemplate.base.analytics.Data
import com.ejsong.apptemplate.base.analytics.ViewBannerEvent
import com.ejsong.apptemplate.base.config.FlagKeys
import com.ejsong.apptemplate.base.ui.AppTheme
import com.ejsong.apptemplate.base.ui.Dimen

/**
 * Home activity that acts as a hub for the user
 */
class HomeActivity : BaseActivity() {

    override val screenTag = HomeActivity::class.simpleName
    private var banner: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        flags.addRealTimeListener(FlagKeys.BANNER, ::updateScreen)

        updateScreen()
    }

    override fun onDestroy() {
        super.onDestroy()

        flags.removeRealTimeListener(FlagKeys.BANNER)
    }

    fun updateScreen() {
        banner = flags.getBanner().ifBlank { null }
        val banner = banner
        if (banner != null) Data.log(ViewBannerEvent(banner))

        setContent {
            AppTheme {
                HomeScreen(
                    banner = banner
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    banner: String?
) {
    Surface {
        Column {
            if (banner != null) {
                Text(
                    text = banner,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(PaddingValues(bottom = Dimen.spacing_med)) // margin
                        .background(color = MaterialTheme.colorScheme.secondaryContainer)
                        .fillMaxWidth()
                )
            }
            Text(text = stringResource(R.string.home_intro))
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() = HomeScreen(
    banner = "This is the banner notification"
)