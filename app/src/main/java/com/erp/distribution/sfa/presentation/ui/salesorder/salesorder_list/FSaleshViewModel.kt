package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_list

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.erp.distribution.sfa.data.di.PreferencesManager
import com.erp.distribution.sfa.data.di.SortOrder
import com.erp.distribution.sfa.data.source.entity.toDomain
import com.erp.distribution.sfa.domain.exception.ExceptionHandler
import com.erp.distribution.sfa.domain.model.FtSalesdItems
import com.erp.distribution.sfa.domain.model.FtSalesh
import com.erp.distribution.sfa.domain.usecase.GetFCustomerUseCase
import com.erp.distribution.sfa.domain.usecase.GetFMaterialUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSalesdItemsUseCase
import com.erp.distribution.sfa.domain.usecase.GetFtSaleshUseCase
import com.erp.distribution.sfa.presentation.base.BaseViewModel
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import com.erp.distribution.sfa.utils.SecurityUtil
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*


class FSaleshViewModel @ViewModelInject constructor(
        private val getFtSaleshUseCase: GetFtSaleshUseCase,
        private val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase,
        private val getFCustomerUseCase: GetFCustomerUseCase,
        private val getFMaterialUseCase: GetFMaterialUseCase,
        private val preferencesManager: PreferencesManager,
        @Assisted private val state: SavedStateHandle

) : BaseViewModel() {
    private val TAG = FSaleshViewModel::class.java.simpleName

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
//        _userViewState.value = _userViewState.value?.copy(error = Error(message))
    }
//    val userViewState = state.get<UserViewState>("userViewStateActive")
    var userViewState = UserViewState()
//    var ftSalesh = FtSalesh() //Menggunakan langsung dari adapter

    val searchQuery = state.getLiveData("searchQuery", "")
    val preferencesFlow = preferencesManager.preferencesFlow

    private val ftSaleshFlow = combine(
            searchQuery.asFlow(),
            preferencesFlow
    ) { query, filterPreferences ->
        Pair(query, filterPreferences)
    }.flatMapLatest { (query, filterPreferences) ->
        getFtSaleshUseCase.getCacheAllFtSaleshFlow(
                query,
                filterPreferences.sortOrder, 75, -1,
                filterPreferences.hideCompleted
        )
    }


    val ftSaleshLive = ftSaleshFlow.asLiveData()
//    val ftSaleshLive = getFtSaleshUseCase.getCacheAllFtSaleshLive()

//    fun getFtSaleshWithTransform(): LiveData<List<FtSalesh>> {
//        var resultLiveData: LiveData<List<FtSalesh>> = ftSaleshLive
//
//        resultLiveData = Transformations.switchMap(resultLiveData, {
//            conversionAddItems2(it)
//        })
//        return resultLiveData
//    }

    val ftSaleshWithItemsLive = getFtSaleshUseCase.getCacheAllFtSaleshWithItemsLive()



    fun onSortOrderSelected(sortOrder: SortOrder) = viewModelScope.launch {
        preferencesManager.updateSortOrder(sortOrder)
    }

    fun onHideCompletedClick(hideCompleted: Boolean) = viewModelScope.launch {
        preferencesManager.updateHideCompleted(hideCompleted)
    }

    fun onItemCheckedChanged(ftSalesh: FtSalesh, isChecked: Boolean) = viewModelScope.launch {
        updateCacheFtSalesh(ftSalesh.copy(selected = isChecked))
    }

    fun updateCacheFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.putCacheFtSalesh(ftSalesh)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},
                        {
//                            Log.e(TAG, "#result Error Update ${it}")
                        }, {}
                )
        )
    }

    fun updateCacheFtSalesd_FromRepo(ftSalesh: FtSalesh, list: List<FtSalesdItems>) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSalesdItemsUseCase.deleteAllCacheFtSalesdItemsByFtSalesh(ftSalesh).also {
                for (data in list){
                    data.ftSaleshBean = ftSalesh
                    getFtSalesdItemsUseCase.addCacheFtSalesdItems(data)
                }
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {},
                        {
//                            Log.e(TAG, "#result Error Update ${it}")
                        }, {}
                )
        )
    }


    fun onItemSelected(ftSalesh: FtSalesh) = viewModelScope.launch {
        userViewState?.let {
            ftSaleshEventChannel.send(FtSaleshEvent.NavigateToEditCustomerOrderScreen(userViewState!!, ftSalesh))
        }
    }
    fun onItemSwiped(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.deleteCacheFtSalesh(ftSalesh)
            getFtSalesdItemsUseCase.deleteAllCacheFtSalesdItemsByFtSalesh(ftSalesh)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                        },
                        {
                        }, {}
                )
        )

        ftSaleshEventChannel.send(FtSaleshEvent.ShowUndoDeleteFtSaleshMessage(ftSalesh))
    }

    fun onUndoDeleteClick(ftSalesh: FtSalesh) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            getFtSaleshUseCase.addCacheFtSalesh(ftSalesh).also {
                getFtSalesdItemsUseCase.addCacheListFtSalesdItems(ftSalesh.listFtSalesdItems)
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                        },
                        {
//                            Log.d(TAG, "#result FtSalesh error  ${it.message}")
                        }, {}
                )
        )
    }

    fun onAddNewFtSaleshClick() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToEditCustomerOrderScreen(userViewState!!, FtSalesh()))
    }

    fun onAddEditResult(ftSaleshResult: FtSalesh) = viewModelScope.launch {
        ftSaleshResult?.let {
//            ftSaleshResult ->  ftSalesh
        }

//        when (result) {
//            ADD_TASK_RESULT_OK -> showFtSaleshSavedConfirmationMessage("Data added")
//            EDIT_TASK_RESULT_OK -> showFtSaleshSavedConfirmationMessage("Data updated")
//        }

    }


    fun onSyncOrUploadToServer(text: String) = viewModelScope.launch {

        /**
         * Percobaan ke-1
         * 1. Upload FtSalesh -> Menghasilkan Refno Baru
         * 2. Modifikasi List<FtSalesd> dengan Refno baru tersebut
         * 3. Upload List<FtSalesd> -> Mendapatkan nilai balik Untuk Message Error jika tidak berhasil
         * 4. Status Uploaded (Active)
         */

        getFtSaleshUseCase.getCacheAllFtSaleshWithItemsSingle()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext {
                            for (ftSaleshBean in it.filter { it.listFtSalesdItems.size >0  }){
//                                if (ftSaleshBean.listFtSalesdItems.size > 0 ) {

                                if (ftSaleshBean.orderno.equals(""))
                                    ftSaleshBean.orderno = "NewMobile"

                                if (ftSaleshBean.sourceId == 0.toLong())
                                    ftSaleshBean.sourceId = System.currentTimeMillis()


                                /**
                                 * Ingat createRemoteFtSaleshFromAndroid Android lho ya
                                 */
                                val authHeader = SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm)
                                    getFtSaleshUseCase
                                            .addOrUpdateRemoteFtSaleshFromAndroid(authHeader, ftSaleshBean)
                                            .toObservable()
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribeOn(Schedulers.io())
                                            .doOnNext { ftSaleshRepo ->
                                                /**
                                                 * refno sudah berubah dengan refno dari server
                                                 */
                                                ftSaleshBean.stared = true
                                                if (! ftSaleshRepo.orderno.trim().toLowerCase().contains("new") && ! ftSaleshRepo.orderno.trim().equals(""))  {
                                                    val ftSalesh = ftSaleshBean.copy(stared=true, unread = false, orderno = ftSaleshRepo.orderno,
                                                            orderDate = ftSaleshRepo.orderDate, invoiceDate = ftSaleshRepo.invoiceDate, dueDate = ftSaleshRepo.dueDate,
                                                            sjPengirimanDate = ftSaleshRepo.sjPengirimanDate, sjPenagihanDate = ftSaleshRepo.sjPenagihanDate,
                                                            disc1 = ftSaleshRepo.disc1, disc2 = ftSaleshRepo.disc2, discPlus_FG = ftSaleshRepo.discPlus_FG,


                                                            invoiceno = ftSaleshRepo.invoiceno, amountAfterDiscPlusRpAfterPpn_FG = ftSaleshRepo.amountAfterDiscPlusRpAfterPpn_FG)
                                                    //LANJUTKAN DIATAS NANTI YA
                                                    updateCacheFtSalesh(ftSalesh)

                                                    getFtSalesdItemsUseCase.getRemoteAllFtSalesdItemsByFtSalesh(authHeader, ftSaleshRepo.refno)
                                                            .toObservable()
                                                            .observeOn(AndroidSchedulers.mainThread())
                                                            .subscribeOn(Schedulers.io())
                                                            .subscribe (
                                                                { listFtSalesdItems ->
                                                                    updateCacheFtSalesd_FromRepo(ftSalesh, listFtSalesdItems)
                                                                },
                                                                    {
//                                                                        Log.e(TAG, "#result Error Update FtSalesd")
                                                                    }, {}
                                                            )

                                                }else {
                                                    updateCacheFtSalesh(ftSaleshBean.copy(stared=true))

//                                                Log.d(TAG, "#result OnNext:\n ${it.refno} ")

                                                    for (ftSalesdItems in ftSaleshBean.listFtSalesdItems) {
                                                        ftSalesdItems.ftSaleshBean = ftSaleshRepo!!
                                                        getFtSalesdItemsUseCase.createRemoteFtSalesdItems(SecurityUtil.getAuthHeader(userViewState.fUser!!.username, userViewState.fUser!!.passwordConfirm), ftSalesdItems)
                                                                .toObservable()
                                                                .observeOn(AndroidSchedulers.mainThread())
                                                                .subscribeOn(Schedulers.io())
                                                                .subscribe({
//                                                                Log.d(TAG, "#result Items Success ${it}")
                                                                }, {
//                                                                Log.e(TAG, "#result  Items Error ${it}")
                                                                }, {})
                                                    }
                                                }


                                            }
                                            .subscribe({
                                                /**
                                                 * Change Status of FtSalesh
                                                 */

                                                
//                                                Log.d(TAG, "#result OnSubscribe:\n ${it.refno} ")

                                            }, {
//                                                Log.e(TAG, "#result Error OnSubscribe:\n ${it} ")
                                            }, {})
//                                }
                            }//endfor
                }
                .subscribe({}, {
//                    Log.e(TAG, "#result Error subscribe:\n ${it} ")
                }, {})


        ftSaleshEventChannel.send(FtSaleshEvent.ShowInfoMessage(text))

    }

    private fun showFtSaleshSavedConfirmationMessage(text: String) = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.ShowFtSaleshSavedConfirmationMessage(text))
    }

    fun onDeleteAllCompletedClick() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateToDeleteAllCompletedScreen)
    }

    fun popUpBackStackWithTheResult() = viewModelScope.launch {
        ftSaleshEventChannel.send(FtSaleshEvent.NavigateBackWithResult(EDIT_TASK_RESULT_OK))
    }


    /**
     * Model Events
     */
    private val ftSaleshEventChannel = Channel<FtSaleshEvent>()
    val ftSaleshEvent = ftSaleshEventChannel.receiveAsFlow()

    sealed class FtSaleshEvent {
//        object NavigateToAddSalesOrderScreen() : FtSaleshEvent()

        data class NavigateToAddCustomerOrderScreen(val userViewState: UserViewState) : FtSaleshEvent()

        /**
         * Ingat FtSalesh disini sudah include List FtSalesItems sebab saat dari tarik dari database sudah dalam keadaan berelasi antar tabel
         */
        data class NavigateToEditCustomerOrderScreen(val userViewState: UserViewState, val ftSalesh: FtSalesh) : FtSaleshEvent()
//        data class NavigateToEditSalesOrderScreen(val userViewState: UserViewState, val ftSaleshBean: Long) : FtSaleshEvent() //--> Sebaiknya nanti gunakan Via Database Langsung

        data class ShowUndoDeleteFtSaleshMessage(val ftSalesh: FtSalesh) : FtSaleshEvent()
        data class ShowInfoMessage(val msg: String) : FtSaleshEvent()
        data class ShowFtSaleshSavedConfirmationMessage(val msg: String) : FtSaleshEvent()

        object NavigateToDeleteAllCompletedScreen : FtSaleshEvent()
        data class NavigateBackWithResult(val result: Int) : FtSaleshEvent()
    }


    /**
     * THIS IS THE OTHER WAY
     * RELATION SHIP WITHOUT TABLE RELATIONSHIP
     *
     */
    fun getFtSaleshWithFCustomerLive(): LiveData<List<FtSalesh>> {
        var resultLiveData: LiveData<List<FtSalesh>> = ftSaleshLive

        resultLiveData = Transformations.switchMap(resultLiveData, {
            conversionFtSaleshWithFCustomer(it)
        })

        return resultLiveData
    }

    fun conversionFtSaleshWithFCustomer(list: List<FtSalesh>) : LiveData<List<FtSalesh>> {
        val resultMediatorLiveData: MediatorLiveData<List<FtSalesh>> = MediatorLiveData<List<FtSalesh>>()
        for (data in list) {
            resultMediatorLiveData.addSource(getFCustomerUseCase.getCacheFCustomerById(data.fcustomerBean.id), Observer {
                it?.let {
                    data.fcustomerBean = it
                    resultMediatorLiveData.postValue(list)
                }
            }
            )
        }
        return resultMediatorLiveData
    }

    fun conversionAddItems(list: List<FtSalesh>) : LiveData<List<FtSalesh>> {
        val resultMediatorLiveData: MediatorLiveData<List<FtSalesh>> = MediatorLiveData<List<FtSalesh>>()
        for (data in list) {
            resultMediatorLiveData.addSource(getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshLive(data.refno), Observer {
                it?.let {
                    data.listFtSalesdItems.addAll(it)
                    resultMediatorLiveData.postValue(list)
//                    Log.d(TAG, "#result ::>> ${it.size}")
                }
            })
        }
        return resultMediatorLiveData
    }

    fun conversionAddItems2(list: List<FtSalesh>) : LiveData<List<FtSalesh>> {
        val resultMediatorLiveData: MediatorLiveData<List<FtSalesh>> = MediatorLiveData<List<FtSalesh>>()
        resultMediatorLiveData.value = list
        for (data in list) {
            resultMediatorLiveData.addSource(getFtSalesdItemsUseCase.getCacheListFtSalesdItemsByFtSaleshLive(data.refno), Observer {
                it?.let {
                    data.listFtSalesdItems.addAll(it)
//                    Log.d(TAG, "#result ::>> ${it.size}")
                    resultMediatorLiveData.postValue(list)
                }
            })
        }
        return resultMediatorLiveData
    }



}


class CombinedSubjectReminders(
        ldSubject: LiveData<List<FtSalesh>>,
        ldReminder: LiveData<List<FtSalesdItems>>
) : MediatorLiveData<Pair<List<FtSalesh>, List<FtSalesdItems>>>() {

    private var listSubject: List<FtSalesh> = emptyList()
    private var listReminder: List<FtSalesdItems> = emptyList()

    init {
        value = Pair(listSubject, listReminder)

        addSource(ldSubject) {
            if( it != null ) listSubject = it
            value = Pair(listSubject, listReminder)
        }

        addSource(ldReminder) {
            if( it != null ) listReminder = it
            value = Pair(listSubject, listReminder)
        }
    }
}