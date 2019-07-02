package ms.ralph.coroutinessample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ms.ralph.coroutinessample.util.coroutines.LifecycleScope
import ms.ralph.coroutinessample.util.coroutines.LifecycleScopeSupport

class MainFragment : Fragment(), LifecycleScopeSupport {

    override val scope = LifecycleScope(this)

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter()
        presenter.onCreate(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    fun showText(text: String) {
        view?.findViewById<TextView>(R.id.textView)?.text = text
    }
}
