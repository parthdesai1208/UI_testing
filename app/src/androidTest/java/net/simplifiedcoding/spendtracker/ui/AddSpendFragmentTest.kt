package net.simplifiedcoding.spendtracker.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import net.simplifiedcoding.spendtracker.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddSpendFragmentTest {

    //Purpose : here we want to test UI for add spend scenario & check success message

    //scenario = instance of AddSpendFragment class
    //FragmentScenario provides "Fragment's lifecycle state for testing"
    private lateinit var scenario: FragmentScenario<AddSpendFragment>

    @Before
    fun setup() {
        //launchFragmentInContainer = Launches a Fragment in the Activity's root view container
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_SpendTracker)
        //define state below
        //for testing fragment in STARTED state
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testAddingSpend() {
        //here we have two inputs for testing
        val amount = 100
        val desc = "Bought Eggs"
        //withId = to access view id
        //1) setting amount = 100 to edit_text_amount editText
        onView(withId(R.id.edit_text_amount)).perform(typeText(amount.toString()))
        //2) setting desc = "Bought Eggs" to edit_text_description editText
        onView(withId(R.id.edit_text_description)).perform(typeText(desc))
        //3) now we enter both inputs. so now close the keyboard
        Espresso.closeSoftKeyboard()
        //onView(withId(R.id.edit_text_description)).perform(closeSoftKeyboard())
        //4) now its time to hit "button_add" button for add spend
        onView(withId(R.id.button_add)).perform(click())

        //now we are checking that weather we get the proper message or not
        //5) check expected message(same define in AddSpendFragment class(44:line)) on the textView "text_view_success_message"
        onView(withId(R.id.text_view_success_message)).check(matches(withText("Spend Added")))

        //hurray, now its time to run the test, hit green button of the testAddingSpend function and
        //make sure your testing device is running because it requires real device
    }
}