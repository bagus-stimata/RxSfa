package com.erp.distribution.sfa.presentation.ui.salesorder.salesorder_qty

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.erp.distribution.sfa.domain.model.*
import com.erp.distribution.sfa.domain.usecase.*
import com.erp.distribution.sfa.domain.utils.KonversiProductAndStockHelper
import com.erp.distribution.sfa.domain.utils.KonversiProductAndStockHelperImpl
import com.erp.distribution.sfa.presentation.model.UserViewState
import com.erp.distribution.sfa.presentation.ui.salesorder.ADD_TASK_RESULT_OK
import com.erp.distribution.sfa.presentation.ui.salesorder.EDIT_TASK_RESULT_OK
import com.erp.distribution.sfa.utils.DisposableManager
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditFtSaleshQtyViewModel @ViewModelInject constructor(
    private val getFtSaleshUseCase: GetFtSaleshUseCase,
    private val getFtSalesdItemsUseCase: GetFtSalesdItemsUseCase,
    private val getFStockUseCase: GetFStockUseCase,
    private val getFtPriceAltdItemsUseCase: GetFtPriceAltdItemsUseCase,
    private val getFCustomerUseCase: GetFCustomerUseCase,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    val TAG = AddEditFtSaleshQtyViewModel::class.java.simpleName

    var userViewState = UserViewState()

    var ftSaleshRefno: Long = 0
    var ftSalesh = FtSalesh()
    var ftSalesdItemsId: Long = 0
    var ftSalesdItems = FtSalesdItems()

    fun getStockFromRepoAndUpdateToCache(fMaterial: FMaterial): LiveData<List<FStock>> {
        //Sementara From Cache Dahulu
        return getFStockUseCase.getCacheFStockByFMaterial(fMaterial.id)
//        return getFStockUseCase.getCacheFStockByFMaterial(fMaterial.id)

    }
//    var mutableLiveFtSalesdItems = MutableLiveData<FtSalesdItems>()

//    val userViewState = state.get<UserViewState>("userViewStateActive")
//    val ftSalesh = state.get<FtSalesh>("ftSalesh")

//    var ftSaleshName = state.get<String>("ftSaleshName") ?: ftSalesh?.invoiceno ?: ""
//        set(value) {
//            field = value
//            state.set("ftSaleshName", value)
//        }
//
//    var ftSaleshImportance = state.get<Boolean>("statusActive") ?: ftSalesh?.isValidOrder ?: false
//        set(value) {
//            field = value
//            state.set("statusActive", value)
//        }

    private val addEditFtSaleshEventChannel = Channel<AddEditFtSaleshQtyEvent>()
    val addEditFtSaleshEvent = addEditFtSaleshEventChannel.receiveAsFlow()

    fun onSaveClick() {
    }

    private fun addOrUpdateFtSalesdItems(ftSalesdItems: FtSalesdItems) = viewModelScope.launch {
        DisposableManager.add(Observable.fromCallable {
            if (ftSalesdItems.id >0){
                getFtSalesdItemsUseCase.putCacheFtSalesdItems(ftSalesdItems)
            }else {
                getFtSalesdItemsUseCase.addCacheFtSalesdItems(ftSalesdItems)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                },
                {
                    Log.d(TAG, "#result MATERIAL error  ${it.message}")
                },{}
            )
        )

        addEditFtSaleshEventChannel.send(
            AddEditFtSaleshQtyEvent.NavigateBackWithResult(
                ADD_TASK_RESULT_OK
            )
        )
    }

    private fun updateFtSalesh(ftSalesh: FtSalesh) = viewModelScope.launch {

        addEditFtSaleshEventChannel.send(
            AddEditFtSaleshQtyEvent.NavigateBackWithResult(
                EDIT_TASK_RESULT_OK
            )
        )

    }

    fun onPopUpBackStackWithTheResult() = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(
            AddEditFtSaleshQtyEvent.NavigateBackWithResult(
                EDIT_TASK_RESULT_OK
            )
        )
    }

    fun getHargaAlternatif_FromCacheLive(ftPricehAlthBean: Int): LiveData<List<FtPriceAltdItems>> {
        return  getFtPriceAltdItemsUseCase.getCacheAllFtPriceAltdItemsByFtPriceAlthAndFMaterial(ftPricehAlthBean, ftSalesdItems.fmaterialBean.id)
    }
    fun getFCustomerWithGroup_FromCacheLive(fcustomerBean: Int): LiveData<FCustomer> {
        return getFCustomerUseCase.getCacheFCustomerById(fcustomerBean)
    }

    fun onUpdateQtySave() = viewModelScope.launch {
        userViewState?.let {
               ftSalesh
        }?.let {
            ftSalesdItems
        }?.let {
            ftSalesdItems.fmaterialBean
        }?.run {
            if (ftSalesh.refno>0 && ftSalesdItems.fmaterialBean.id >0) {
                ftSalesdItems.ftSaleshBean = ftSalesh
                val kps : KonversiProductAndStockHelper = KonversiProductAndStockHelperImpl()
                var qtySmallestFromUom1234 = kps.getSmallestFromUom1234(ftSalesdItems.fmaterialBean, ftSalesdItems.qty1, ftSalesdItems.qty2, ftSalesdItems.qty3, ftSalesdItems.qty4)
//                ftSalesdItems.qty = ftSalesdItems.qty1+ftSalesdItems.qty2+ftSalesdItems.qty3+ftSalesdItems.qty4
                ftSalesdItems.qty = qtySmallestFromUom1234

                if (ftSalesdItems.fmaterialBean.taxable) {
                    ftSalesdItems.tax = true
                    ftSalesdItems.taxPercent = 10.0
                }

                addOrUpdateFtSalesdItems(ftSalesdItems)

                addEditFtSaleshEventChannel.send(AddEditFtSaleshQtyEvent.NavigateToFtSaleshCustomerOrder(userViewState!!, ftSalesh, ftSalesdItems))
            }
        }
//        addEditFtSaleshEventChannel.send(AddEditFtSaleshQtyEvent.NavigateToFtSaleshCustomerOrder(userViewState!!, ftSalesh, ftSalesdItems))

    }

    fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        addEditFtSaleshEventChannel.send(AddEditFtSaleshQtyEvent.ShowInvalidInputMessage(text))
    }

    sealed class AddEditFtSaleshQtyEvent {
        data class ShowInvalidInputMessage(val msg: String) : AddEditFtSaleshQtyEvent()

        data class NavigateToFtSaleshCustomerOrder(var userViewState: UserViewState, val ftSalesh: FtSalesh, val ftSalesdItems: FtSalesdItems) : AddEditFtSaleshQtyEvent()
        data class NavigateBackWithResult(val result: Int) : AddEditFtSaleshQtyEvent()
    }

}