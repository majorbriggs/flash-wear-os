/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.majorbriggs.flash.wear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.majorbriggs.flash.wear.presentation.screen.FlashCardScreen
import com.majorbriggs.flash.wear.presentation.screen.WordListScreen
import com.majorbriggs.flash.wear.presentation.theme.FlashTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlashWearActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashTheme {
                val navController = rememberSwipeDismissableNavController()
                SwipeDismissableNavHost(
                    navController = navController,
                    startDestination = "word_list"
                ) {
                    composable("word_list") {
                        WordListScreen(onWordSelected = { id ->
                            navController.navigate("flash_card/$id")
                        })
                    }
                    composable("flash_card/{id}") {
                        FlashCardScreen()
                    }
                }
            }
        }
    }
}
