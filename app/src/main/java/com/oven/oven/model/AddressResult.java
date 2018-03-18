package com.oven.oven.model;

import java.util.List;

/**
 * Created by sung9 on 2018-01-29.
 */

public class AddressResult {
    private AddressMeta meta;

    public AddressMeta getMeta() {
        return meta;
    }

    public void setMeta(AddressMeta meta) {
        this.meta = meta;
    }

    public List<AddressDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<AddressDocument> documents) {
        this.documents = documents;
    }

    private List<AddressDocument> documents;
}
