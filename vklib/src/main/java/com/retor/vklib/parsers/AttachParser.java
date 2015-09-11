package com.retor.vklib.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.retor.vklib.model.Attachments;
import com.retor.vklib.model.attachments.AttachModel;
import com.retor.vklib.model.attachments.Document;
import com.retor.vklib.model.attachments.Photo;

import java.lang.reflect.Type;

/**
 * Created by retor on 02.09.2015.
 */
public class AttachParser implements JsonDeserializer<Attachments<AttachModel>> {
    @Override
    public Attachments<AttachModel> deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        Attachments<AttachModel> out = null;
        for (JsonElement element : array) {
            String type = element.getAsJsonObject().get("type").getAsString();
            if (type.equals("photo")) {
                JsonObject photo = element.getAsJsonObject().getAsJsonObject("photo");
                Photo ph = getPhoto(photo.toString());
                out = new Attachments<>((AttachModel) ph);
            }
            if (type.equals("doc")) {
                JsonObject document = element.getAsJsonObject().getAsJsonObject("doc");
                Document doc = getDoc(document.toString());
                out = new Attachments<>((AttachModel) doc);
            }
        }
        return out;
    }

    private Document getDoc(String input) {
        Type listType = new TypeToken<Document>() {
        }.getType();
        return new Gson().fromJson(input, listType);
    }

    private Photo getPhoto(String input) {
        Type listType = new TypeToken<Photo>() {
        }.getType();
        return new Gson().fromJson(input, listType);
    }
}
