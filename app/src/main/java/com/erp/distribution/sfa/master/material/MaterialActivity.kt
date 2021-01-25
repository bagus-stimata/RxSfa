package com.erp.distribution.sfa.master.material

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erp.distribution.sfa.model.FMaterial
import com.erp.distribution.sfa.security_model.FUser
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.Predicate
import java.util.stream.Collectors
import com.erp.distribution.sfa.R
import com.erp.distribution.sfa.common_utils.AlertDialogWarning
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.HashMap

@AndroidEntryPoint
class MaterialActivity : AppCompatActivity() {
//    var apiAuthenticationClient: ApiAuthenticationClient? = null

    var userActive: FUser = FUser()
    var fMaterialViewModel: MaterialViewModel? = null
    private val adapter = MaterialAdapter()
    lateinit var recyclerView: RecyclerView
    var mapSource: MutableMap<Int?, FMaterial?> = HashMap<Int?, FMaterial?>()
    var searchText = ""
    lateinit var rootLayout: RelativeLayout

    interface OnViewListener {
        fun aksiButtonFormSave()
        fun aksiTableItemClick(note: FMaterial?)
    }

    var onViewListener: OnViewListener? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_template1)
        rootLayout = findViewById<RelativeLayout>(R.id.rootLayout)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        fMaterialViewModel =
            ViewModelProvider(this).get<MaterialViewModel>(MaterialViewModel::class.java)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view_customer)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.setHasFixedSize(true)
        recyclerView.setAdapter(adapter)
        //        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Tambah Devider dibawah setiap Item
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rv_divider))
        recyclerView.addItemDecoration(dividerItemDecoration)
        inizialize()
    }

    fun inizialize() {
        initListener()
    }

    fun initListener() {
//        FloatingActionButton buttonAddFMaterial = findViewById(R.id.button_add_note);
//        buttonAddFMaterial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddEditFMaterialActivity.class);
//                startActivityForResult(intent, ADD_NOTE_REQUEST);
//            }
//        });

//        adapter.setOnItemClickListener(new FMaterialAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(FMaterial note) {
//                onViewListener.aksiTableItemClick(note);
//            }
//        });


//        for (fMaterial in fMaterialViewModel!!.allFMaterial) {
//            mapSource[fMaterial.id] = fMaterial
//        }
//        adapter.submitList(ArrayList<FMaterial>(mapSource.values))

//        noteViewModel.getAllFMaterialLive().observe(this, new Observer<List<FMaterial>>() {
//            @Override
//            public void onChanged(@Nullable List<FMaterial> notes) {
//
//                listSource = new ArrayList<>(notes);
//                adapter.submitList(notes.stream().filter(x->x.getCustname().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList()));
//
//            }
//        });
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedFMaterial: FMaterial =
                    fMaterialViewModel!!.delete(adapter.getFMaterialAt(viewHolder.getAdapterPosition())!!)
                mapSource.remove(deletedFMaterial.id)
                adapter.submitList(ArrayList<FMaterial>(mapSource.values))

                // showing snack bar with Undo option
                val snackbar: Snackbar = Snackbar
                    .make(rootLayout, "Terhapus dari daftar!", Snackbar.LENGTH_LONG)
                snackbar.setAction("UNDO", View.OnClickListener {
                    fMaterialViewModel!!.insert(deletedFMaterial)
                    mapSource[deletedFMaterial.id] = deletedFMaterial
                    adapter.submitList(ArrayList<FMaterial>(mapSource.values))
                })
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
                Toast.makeText(this@MaterialActivity, "FMaterial deleted", Toast.LENGTH_SHORT)
                    .show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_menu, menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchText = newText
                //                Log.d("Ini Filter", newText);
                if (newText != null) {
                    adapter.submitList(
                        mapSource.values.stream().filter(Predicate<FMaterial?> { x: FMaterial? ->
                            x!!.pname.toLowerCase().contains(newText.toLowerCase())
                        }).collect(
                            Collectors.toList()
                        )
                    )
                    return true
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                true
            }
            R.id.customer_scyncronize -> {
                syncronizeWithRestServer()
                true
            }
            R.id.customer_deleteall -> {
                deleteAllFromDb()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
//            FMaterial domain = (FMaterial) data.getSerializableExtra(AddEditFMaterialActivity.DOMAIN);
//            noteViewModel.insert(domain);
//            Toast.makeText(this, "FMaterial saved", Toast.LENGTH_SHORT).show();
//        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(AddEditFMaterialActivity.EXTRA_ID, -1);
//            if (id == -1) {
//                Toast.makeText(this, "FMaterial can't be updated", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            FMaterial domain = (FMaterial) data.getSerializableExtra(AddEditFMaterialActivity.DOMAIN);
//            domain.setId(id);
//            noteViewModel.update(domain);
//            Toast.makeText(this, "FMaterial updated", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "FMaterial not saved", Toast.LENGTH_SHORT).show();
//        }
    }

    fun formAddEditShow(note: FMaterial?) {
//        Intent intent = new Intent(MainActivity.this, AddEditFMaterialActivity.class);
//        intent.putExtra(AddEditFMaterialActivity.EXTRA_ID, note.getId());
//        intent.putExtra(AddEditFMaterialActivity.DOMAIN, note);
//        startActivityForResult(intent, EDIT_NOTE_REQUEST);
    }

    fun syncronizeWithRestServer() {
    }

    fun deleteAllFromDb() {
        val alert = AlertDialogWarning(this, "Yakin Menghapus Semua Data?")
        alert.getButtonOke().setOnClickListener(View.OnClickListener {
            alert.dismiss()
//            fMaterialViewModel!!.deleteAllFMaterial()
        })
        alert.getButtonCancel()
            .setOnClickListener(View.OnClickListener { view: View? -> alert.dismiss() })
        alert.showDialog()
    }

    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }
}