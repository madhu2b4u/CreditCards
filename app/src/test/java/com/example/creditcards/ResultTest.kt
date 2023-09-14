package com.example.creditcards

import com.example.creditcards.common.Status
import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.creditcards.common.Result

class ResultTest {

    @Test
    fun `success should create a Result with SUCCESS status and data`() {
        val data = "SuccessData"
        val result = Result.success(data)

        assertEquals(Status.SUCCESS, result.status)
        assertEquals(data, result.data)
        assertEquals(null, result.message)
    }

    @Test
    fun `error should create a Result with ERROR status, data, and message`() {
        val errorMessage = "Error Message"
        val data = "ErrorData"
        val result = Result.error(errorMessage, data)

        assertEquals(Status.ERROR, result.status)
        assertEquals(data, result.data)
        assertEquals(errorMessage, result.message)
    }

    @Test
    fun `loading should create a Result with LOADING status`() {
        val result = Result.loading<Any>()

        assertEquals(Status.LOADING, result.status)
        assertEquals(null, result.data)
        assertEquals(null, result.message)
    }
}
