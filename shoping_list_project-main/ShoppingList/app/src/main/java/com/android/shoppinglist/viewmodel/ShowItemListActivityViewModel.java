package com.android.shoppinglist.viewmodel;

import android.app.Application;
import android.content.ClipData;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.shoppinglist.db.AppDatabase;
import com.android.shoppinglist.db.Category;
import com.android.shoppinglist.db.Items;

import java.util.List;

public class ShowItemListActivityViewModel extends AndroidViewModel {

//    private MutableLiveData<List<Items>> listOfItems;
//    private AppDatabase appDatabase;
//
//    public ShowItemListActivityViewModel(Application application) {
//        super(application);
//        listOfItems = new MutableLiveData<>();
//
//        appDatabase = AppDatabase.getDBinstance(getApplication().getApplicationContext());
//    }
//
//    public MutableLiveData<List<Items>>  getItemsListObserver() {
//        return listOfItems;
//    }
//
//    public void getAllItemsList(int categoryID) {
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<Items> itemsList=  appDatabase.shoppingListDao().getAllItemsList(categoryID);
//                if(itemsList.size() > 0)
//                {
//                    listOfItems.postValue(itemsList);
//                }else {
//                    listOfItems.postValue(null);
//                }
//            }
//        });
//
//    }
//
//    public void insertItems(Items item) {
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase.shoppingListDao().insertItems(item);
//
//            }
//        });
//        getAllItemsList(item.categoryId);
//    }
//
//    public void updateItems(Items item) {
//
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase.shoppingListDao().updateItems(item);
//
//            }
//        });
//        getAllItemsList(item.categoryId);
//    }
//
//    public void deleteItems(Items item) {
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase.shoppingListDao().deleteItem(item);
//
//            }
//        });
//        getAllItemsList(item.categoryId);
//    }
    private MutableLiveData<List<Items>> listOfItems;
    public static ShoppingRepository repository;

    public ShowItemListActivityViewModel(Application application) {
        super(application);
        listOfItems = new MutableLiveData<>();
        repository = new ShoppingRepository(application);
    }

    public MutableLiveData<List<Items>>  getItemsListObserver() {
        return repository.getItemsListObserver();
    }

    public void getAllItemsList(int categoryID) {
        repository.getAllItemsList(categoryID);
    }

    public void insertItems(Items item) {
        repository.insertItems(item);
    }

    public void updateItems(Items item) {
        repository.updateItems(item);
    }

    public void deleteItems(Items item) {
        repository.deleteItems(item);
    }

}
