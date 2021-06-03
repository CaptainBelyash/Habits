package com.example.datamodule

import com.example.datamodule.network.JSON.HabitJsonDeserializer
import com.example.datamodule.network.JSON.HabitJsonSerializer
import com.example.domainmodule.HabitEntity
import com.example.domainmodule.enums.HabitType
import com.example.domainmodule.enums.Priority
import org.junit.Assert


import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class JsonUnitTest {
    var serializer: HabitJsonSerializer = HabitJsonSerializer()
    lateinit var habit: HabitEntity
    lateinit var habitAsJsonObj: String

    @Before
    fun setUp(){
        habit = HabitEntity("id", "name", "descrip", Priority.High, 10, HabitType.Good)
        habitAsJsonObj = "{\"color\":0,\"count\":0,\"date\":0,\"description\":\"descrip\",\"done_dates\":[],\"frequency\":10,\"priority\":2,\"title\":\"name\",\"type\":0}"
    }

    @Test
    fun jsonSerializer_shouldCorrectSerializeData(){
        assertEquals(serializer.serialize(habit, null, null).toString(), habitAsJsonObj)
    }
}