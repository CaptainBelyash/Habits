package com.example.datamodule.network.JSON

import com.example.domainmodule.HabitEntity
import com.example.domainmodule.enums.HabitType
import com.example.domainmodule.enums.Priority
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HabitJsonDeserializer : JsonDeserializer<HabitEntity> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): HabitEntity {
        val jsonObj = json.asJsonObject

        val habit = HabitEntity(
            jsonObj.get("uid").asString,
            jsonObj.get("title").asString,
            jsonObj.get("description").asString,
            enumValues<Priority>()[jsonObj.get("priority").asInt],
            jsonObj.get("frequency").asInt,
            enumValues<HabitType>()[jsonObj.get("type").asInt]
        )
        habit.uid = jsonObj.get("uid").asString
        return habit
    }
}