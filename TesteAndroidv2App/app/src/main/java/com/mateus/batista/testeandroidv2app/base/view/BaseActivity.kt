package com.mateus.batista.testeandroidv2app.base.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mateus.batista.testeandroidv2app.R
import com.mateus.batista.testeandroidv2app.app.BankApplication
import com.mateus.batista.testeandroidv2app.app.Constants.Service.Companion.SERVER_ERROR
import com.mateus.batista.testeandroidv2app.app.di.ApplicationComponent
import com.mateus.batista.testeandroidv2app.base.viewModel.ViewModelFactory
import com.mateus.batista.testeandroidv2app.extensions.NoInternetException
import com.mateus.batista.testeandroidv2app.extensions.RemoteDataNotFoundException
import javax.inject.Inject


open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as BankApplication).appComponet
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    fun callActivityNewTask(activity: Class<*>) {
        val intent = Intent()
        intent.setClass(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun handleErrors(throwable: Throwable) {
        when (throwable) {
            is RemoteDataNotFoundException -> handleHttpErrors(throwable)
            is NoInternetException -> showDialogError(resources.getString(R.string.no_internet_connection))
            else -> throwable.message?.let { showDialogError(resources.getString(R.string.undefined_error)) }
        }
    }

    private fun handleHttpErrors(throwable: RemoteDataNotFoundException) {
        when (throwable.hashCode()) {
            SERVER_ERROR -> showDialogError(resources.getString(R.string.server_error))
            else -> throwable.message?.let { showDialogError(resources.getString(R.string.undefined_error)) }
        }
    }

    private fun showDialogError(text: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.error_title))
        builder.setMessage(text)
        builder.setNeutralButton(R.string.ok) { dialog, _ ->
            dialog.cancel()
        }
        val alert: AlertDialog = builder.create()
        alert.show()
    }
}