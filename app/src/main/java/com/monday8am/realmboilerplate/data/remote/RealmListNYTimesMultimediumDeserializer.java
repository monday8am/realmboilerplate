package com.monday8am.realmboilerplate.data.remote;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.monday8am.realmboilerplate.data.model.NYTimesMultimedium;

import java.io.IOException;
import java.util.List;

import io.realm.RealmList;

/**
 * Custom deserializer.
 */

public class RealmListNYTimesMultimediumDeserializer
        extends JsonDeserializer<List<NYTimesMultimedium>> {

    private ObjectMapper mObjectMapper;

    public RealmListNYTimesMultimediumDeserializer() {
        mObjectMapper = new ObjectMapper();
    }

    @Override
    public List<NYTimesMultimedium> deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        RealmList<NYTimesMultimedium> list = new RealmList<>();

        TreeNode treeNode = jp.getCodec().readTree(jp);
        if (!(treeNode instanceof ArrayNode)) {
            return list;
        }

        ArrayNode arrayNode = (ArrayNode) treeNode;
        for (JsonNode node : arrayNode) {
            NYTimesMultimedium nyTimesMultimedium =
                    mObjectMapper.treeToValue(node, NYTimesMultimedium.class);
            list.add(nyTimesMultimedium);
        }
        return list;
    }
}
