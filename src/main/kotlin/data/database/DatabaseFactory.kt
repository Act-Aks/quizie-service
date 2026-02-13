package com.actaks.data.database

import com.actaks.data.util.Constant
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

object DatabaseFactory {
    fun create(): MongoDatabase {
        val connectionString =
            System.getenv("DATABASE_URL") ?: throw IllegalArgumentException("Database url is not set")
        val mongoClient = MongoClient.create(connectionString)
        return mongoClient.getDatabase(Constant.DATABASE_NAME)
    }
}