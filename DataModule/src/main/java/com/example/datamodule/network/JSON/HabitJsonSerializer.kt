package com.example.datamodule.network.JSON

import com.example.domainmodule.HabitEntity
import com.google.gson.*
import java.lang.reflect.Type

class HabitJsonSerializer : JsonSerializer<com.example.domainmodule.HabitEntity> {
    override fun serialize(src: com.example.domainmodule.HabitEntity, typeOfSrc: Type?, context: JsonSerializationContext?)
    : JsonElement {
        val jsonArray = JsonArray()
        for (doneData in src.doneDates)
            jsonArray.add(doneData)
        return JsonObject().apply {
            addProperty("color", src.color)
            addProperty("count", src.count)
            addProperty("date", src.date)
            addProperty("description", src.description)
            add("done_dates", jsonArray)
            addProperty("frequency", src.frequency)
            addProperty("priority", src.priority.ordinal)
            addProperty("title", src.name)
            addProperty("type", src.type.ordinal)
            if (src.uid != null)
                addProperty("uid", src.uid)
        }
    }
}