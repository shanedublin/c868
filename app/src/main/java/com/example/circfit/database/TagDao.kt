package com.example.circfit.database

import androidx.room.*
import com.example.circfit.entities.Tag

@Dao
interface TagDao {

    @Query("SELECT * FROM  tag")
    fun getAll(): List<Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tag: Tag)

    @Delete
    fun delete(tag: Tag)

}