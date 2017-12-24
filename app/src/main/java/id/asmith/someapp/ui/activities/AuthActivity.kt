package id.asmith.someapp.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import id.asmith.someapp.R
import id.asmith.someapp.data.local.SQLHandler
import id.asmith.someapp.ui.fragments.AuthLockFragment
import id.asmith.someapp.ui.fragments.AuthSigninFragment
import org.jetbrains.anko.toast

class AuthActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val userDetail = SQLHandler(this).getUser()
        val uid = userDetail["uid"]

        // Check that the activity is using the layout
        if (savedInstanceState == null) {
            if (uid == null) {
                toast("user belum pernah login atau data sudah dihapus")
                changeFragment(AuthSigninFragment())
            } else {
                toast("user sudah pernah login data disimpan")
                changeFragment(AuthLockFragment())
            }

        }
    }

    private fun changeFragment (f: Fragment, cleanStack: Boolean = false) {

        val ft = supportFragmentManager.beginTransaction()

        if (cleanStack) {
            clearBackStack()
        }

        //ft.setCustomAnimations(
        // R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.replace(R.id.fragment_container, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun clearBackStack() {

        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {

        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }

    }

}
