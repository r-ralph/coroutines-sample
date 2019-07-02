package ms.ralph.coroutinessample

class MainPresenter(
    private val useCase: MainUseCase = MainUseCase()
) {

    private var view: MainFragment? = null

    fun onCreate(view: MainFragment) {
        this.view = view
    }

    fun onViewCreated() {
        showSomething()
    }

    fun onDestroy() {
        view = null
    }

    private fun showSomething() {
        view?.bindLaunch {
            val text = useCase.sleepAndReturnTime(1000)
            view?.showText(text)
        }
    }
}
