package net.simplifiedcoding.spendtracker.ui

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import net.simplifiedcoding.spendtracker.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//Large tests should be focused on testing all application components
//use of all resources such as databases, file systems and network
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    //Purpose : here click $ button, open add spend screen, enter amount & desc.,
    //& check weather both are displayed or not

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup(){
        //for launching activity
        scenario = launchActivity()
        ////for testing fragment in RESUMED state
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testAddAndViewSpend(){
        //1) click on "$" button
        onView(withId(R.id.button_add_spend)).perform(click())
        val amount = 100
        val desc = "Bought Eggs"
        //2) enter amount
        onView(withId(R.id.edit_text_amount)).perform(ViewActions.typeText(amount.toString()))
        //3) enter desc
        onView(withId(R.id.edit_text_description)).perform(ViewActions.typeText(desc))
        //4) now close keyboard
        Espresso.closeSoftKeyboard()
        //5) verify amount is displayed or not
        onView(withText(amount.toString())).check(matches(isDisplayed()))
        //6) verify desc is displayed or not
        onView(withText(desc)).check(matches(isDisplayed()))
    }

}