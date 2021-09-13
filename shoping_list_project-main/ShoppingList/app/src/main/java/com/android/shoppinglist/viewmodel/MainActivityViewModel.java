package com.android.shoppinglist.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.android.shoppinglist.db.AppDatabase;
import com.android.shoppinglist.db.Category;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

//    private MutableLiveData<List<Category>> listOfCategory;
//    private AppDatabase appDatabase;
//
//    public MainActivityViewModel(Application application) {
//        super(application);
//        listOfCategory = new MutableLiveData<>();
//
//        appDatabase = AppDatabase.getDBinstance(getApplication().getApplicationContext());
//    }
//
//    public MutableLiveData<List<Category>>  getCategoryListObserver() {
//        return listOfCategory;
//    }
//
//    public void getAllCategoryList() {
//          AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                List<Category> categoryList= appDatabase.shoppingListDao().getAllCategoriesList();
//                if(categoryList.size() > 0)
//                {
//                    listOfCategory.postValue(categoryList);
//                }else {
//                    listOfCategory.postValue(null);
//                }
//            }
//        });
//
//    }
//
//    public void insertCategory(String catName) {
//        Category category = new Category();
//        category.categoryName = catName;
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase.shoppingListDao().insertCategory(category);
//            }
//        });
//
//        getAllCategoryList();
//    }
//
//    public void updateCategory(Category category) {
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase.shoppingListDao().updateCategory(category);
//
//            }
//        });
//        getAllCategoryList();
//    }
//
//    public void deleteCategory(Category category) {
//        AppDatabase.databaseWriterExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                appDatabase.shoppingListDao().deleteCategory(category);
//            }
//        });
//
//        getAllCategoryList();
//    }

    private MutableLiveData<List<Category>> listOfCategory;
    public static ShoppingRepository repository;

    public MainActivityViewModel(Application application) {
        super(application);
        listOfCategory = new MutableLiveData<>();
        repository = new ShoppingRepository(application);
    }

    public MutableLiveData<List<Category>>  getCategoryListObserver() {
        return repository.getCategoryListObserver();
    }

    public void getAllCategoryList() {
        repository.getAllCategoryList();
    }

    public void insertCategory(String catName) {
       repository.insertCategory(catName);
    }

    public void updateCategory(Category category) {
        repository.updateCategory(category);
    }

    public void deleteCategory(Category category) {
       repository.deleteCategory(category);
    }

}
