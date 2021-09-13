package com.android.shoppinglist.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.shoppinglist.db.AppDatabase;
import com.android.shoppinglist.db.Category;
import com.android.shoppinglist.db.Items;
import com.android.shoppinglist.db.ShoppingListDao;

import java.util.List;

public class ShoppingRepository {
    private final ShoppingListDao shoppingListDao;
    private final MutableLiveData<List<Category>> listOfCategory;
    private final MutableLiveData<List<Items>> listOfItems;

    public ShoppingRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getDBinstance(application);
        shoppingListDao = appDatabase.shoppingListDao();
        listOfCategory = new MutableLiveData<>();
        listOfItems = new MutableLiveData<>();
    }

    public MutableLiveData<List<Items>> getItemsListObserver() {
        return listOfItems;
    }

    public MutableLiveData<List<Category>> getCategoryListObserver() {
        return listOfCategory;
    }

    public void getAllCategoryList() {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Category> categoryList = shoppingListDao.getAllCategoriesList();
                if (categoryList.size() > 0) {
                    listOfCategory.postValue(categoryList);
                } else {
                    listOfCategory.postValue(null);
                }
            }
        });

    }

    public void getAllItemsList(int categoryID) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Items> itemsList = shoppingListDao.getAllItemsList(categoryID);
                if (itemsList.size() > 0) {
                    listOfItems.postValue(itemsList);

                } else {
                    listOfItems.postValue(null);
                }
                for (int i = 0; i < itemsList.size(); i++) {
                    Log.d("itemList", "run: " + itemsList.get(i).itemName);
                }
            }
        });

    }


    public void insertCategory(String catName) {
        Category category = new Category();
        category.categoryName = catName;
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.insertCategory(category);
                getAllCategoryList();
            }
        });


    }

    public void insertItems(Items item) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.insertItems(item);
                Log.d("insert", "Item " + item.itemName + "inserted successfully on Thread: " + Thread.currentThread().getName());
                getAllItemsList(item.categoryId);

            }
        });


    }

    public void updateCategory(Category category) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.updateCategory(category);
                getAllCategoryList();
            }
        });

    }

    public void updateItems(Items item) {

        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.updateItems(item);
                getAllItemsList(item.categoryId);
            }
        });

    }

    public void deleteCategory(Category category) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.deleteCategory(category);
                getAllCategoryList();
            }
        });


    }

    public void deleteItems(Items item) {
        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
            @Override
            public void run() {
                shoppingListDao.deleteItem(item);
                Log.d("delete", "The deleted item was: " + item.itemName);
                getAllItemsList(item.categoryId);
            }
        });

    }

}
