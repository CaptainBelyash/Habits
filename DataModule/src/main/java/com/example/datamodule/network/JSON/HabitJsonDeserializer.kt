package com.example.datamodule.network.JSON

import com.example.domainmodule.HabitEntity
import com.example.domainmodule.enums.HabitType
import com.example.domainmodule.enums.Priority
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class HabitJsonDeserializer : JsonDeserializer<com.example.domainmodule.HabitEntity> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): com.example.domainmodule.HabitEntity {
        val jsonObj = json.asJsonObject

        val habit = com.example.domainmodule.HabitEntity(
            jsonObj.get("uid").asString,
            jsonObj.get("title").asString,
            jsonObj.get("description").asString,
            enumValues<com.example.domainmodule.enums.Priority>()[jsonObj.get("priority").asInt],
            jsonObj.get("frequency").asInt,
            enumValues<com.example.domainmodule.enums.HabitType>()[jsonObj.get("type").asInt]
        )
        habit.uid = jsonObj.get("uid").asString
        return habit
    }
}